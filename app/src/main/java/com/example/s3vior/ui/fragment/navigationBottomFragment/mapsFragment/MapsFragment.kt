package com.example.s3vior.ui.fragment.navigationBottomFragment.mapsFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMapsBinding
import com.example.s3vior.domain.model.MafqoudModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {

    private val args: MapsFragmentArgs by navArgs()

    private lateinit var binding: FragmentMapsBinding
    private val mapViewModel: MapViewModel by activityViewModels()
    private var latlongList: List<MafqoudModel>? = null
    private lateinit var mGoogleMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->

        lifecycleScope.launch(Dispatchers.IO) {
            latlongList?.forEach {
                Log.e("TAG", "${it.location.latitude}, ${it.location.longitude}")
                val b = urlToBitmap(it.image!!)
                val a = Glide.with(requireContext())
                    .asBitmap()
                    .load(it.image).into(100, 110).get()

                val bitmapDescriptor = createIcon(a)
                withContext(Dispatchers.Main) {
                    val markerOptions = MarkerOptions()
                        .position(
                            LatLng(
                                it.location.latitude,
                                it.location.longitude
                            )
                        )
                        .title(it.name ?: "")
                        .icon(bitmapDescriptor).anchor(0.5f, 1.0f)
                    val marker = googleMap.addMarker(markerOptions)


                    googleMap.addMarker(
                        markerOptions
                    )
                    googleMap.setOnMarkerClickListener { clickedMarker ->
                        Toast.makeText(activity, clickedMarker.title, Toast.LENGTH_LONG).show()
                        true
                    }
                    mGoogleMap = googleMap;
    val sydney = LatLng(30.4666648, 31.1833326)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

                    val zoomLevel = 7f // specify the desired zoom level
                    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel)
                    mGoogleMap.animateCamera(cameraUpdate)
                }
 
            }
        }

    }

    private fun getPersonDetails() {
        lifecycleScope.launch(Dispatchers.IO) {
            mapViewModel.getPersonDetails(args.personId)
        }

    }

    private fun urlToBitmap(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val input = URL(url).openStream()
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun createIcon(bitmap: Bitmap?): BitmapDescriptor {
        //val originalBitmap =  MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)

        val iconSize = 100
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap!!, iconSize, iconSize, false)

        val circleBitmap = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        val shader = BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        val radius = iconSize / 2.toFloat()
        canvas.drawCircle(radius, radius, radius, paint)

        val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(circleBitmap)

        return bitmapDescriptor
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        lifecycleScope.launch(Dispatchers.IO) {
            mapViewModel.personsStateFlow.collect {
                latlongList = it.toData()
            }

        }
        mapFragment?.getMapAsync(callback)
    }




    override fun onMapReady(googleMap: GoogleMap) {


    }

}