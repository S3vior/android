package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddPhotoLocationBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.Constants
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.ThirdDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar


class AddPhotoLocationFragment : BaseFragment<FragmentAddPhotoLocationBinding>(
    FragmentAddPhotoLocationBinding::inflate
) {
    private lateinit var pickSingleMediaLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickSingleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(context, "Failed picking media.", Toast.LENGTH_SHORT).show()
                } else {
                    uri = it.data?.data!!
                    showSnackBar("SUCCESS: ${uri .path}")
                }
            }
    }
    companion object {
        private val PERMISSIONS_REQUEST_CODE = 123
    }

    private val PERMISSION = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var uri: Uri
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    override fun callFunctions() {
        initButtons()
        callBack()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

    }

    private fun hasLocationPermission(): Boolean {
        return PERMISSION.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(requireActivity(), PERMISSION, PERMISSIONS_REQUEST_CODE)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                val lastLocation = p0.lastLocation
                val latitude = lastLocation?.latitude
                val longitude = lastLocation?.longitude
                Toast.makeText(requireContext(), longitude.toString()+" lat "+latitude.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG","lat :$latitude  long : $longitude")
            }
        }
        if (hasLocationPermission()) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }

    }


    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED })
                startLocationUpdate()
            else {
                Toast.makeText(requireContext(), "denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initButtons() {
        binding.photoFromGallery.setOnClickListener { galleryIntent() }
        binding.photoFromCamera.setOnClickListener { cameraIntent() }
        binding.button5.setOnClickListener {
            if (hasLocationPermission()) {
                startLocationUpdate()
            } else
                requestLocationPermission()
        }
    }

    private fun callBack() {
        binding.next.setOnClickListener {
            try {
                sharedViewModel.getDataFromGalleryFragment(ThirdDetails(uri))
                Navigation.findNavController(it)
                    .navigate(R.id.action_addPhotoLocationFragment_to_personFormFragment)

            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun galleryIntent() {
       // openGalleryForImage()
        openGalleryForPhotoPicker()
    }

     private fun openGalleryForPhotoPicker() {
         openGalleryForImage()

//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
//               pickSingleMediaLauncher.launch(
//                Intent(MediaStore.ACTION_PICK_IMAGES)
//                    .apply {
//                        type = "image/*"
//                    }
//            )
//            }else{
//                openGalleryForImage()
//                showSnackBar("Failed to pick image from")
//        }
    }


    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_CAMERA)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY)
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {


           uri = data.data!!


            data.data?.let { _uri ->
                context?.contentResolver?.query(_uri, null, null, null, null)?.use {
                    if (it.moveToFirst()) {
                        val picturePath = it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
                     //  uri = Uri.parse(picturePath)



              //          Toast.makeText(context,"$uri",Toast.LENGTH_SHORT).show()

                        // uploadToCloudinary(picturePath)
                        Log.d("ajbcjabv", "onActivityResult: $uri ")
                    }
                }
            }

        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG,
        )
        // Set the max lines of SnackBar
        snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines =
            10
        snackBar.show()
    }
}