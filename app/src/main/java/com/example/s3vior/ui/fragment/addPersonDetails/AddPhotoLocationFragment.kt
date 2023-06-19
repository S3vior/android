package com.example.s3vior.ui.fragment.addPersonDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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
                    showSnackBar("تم اضافة الصورة")
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


        val data = sharedViewModel.thirdDetails.value!!.imageUri

        binding.selectedImage.setImageURI(data)
        if (data != null) {
            uri = data
        }



        binding.removeImage.setOnClickListener {
            binding.selectedImage.setImageResource(R.drawable.person_icon_generated)
            uri = Uri.EMPTY
        }
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
                Toast.makeText(
                    requireContext(),
                    longitude.toString() + " lat " + latitude.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("TAG", "lat :$latitude  long : $longitude")
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
                if (uri == Uri.EMPTY) {
                    showSnackBar("من فضلك اضف صورة للمفقود")
                } else {
                    sharedViewModel.getDataFromGalleryFragment(ThirdDetails(uri))

                    Navigation.findNavController(it)
                        .navigate(R.id.action_addPhotoLocationFragment_to_personFormFragment)
                }

            } catch (e: Exception) {
                showSnackBar("من فضلك اضف صورة للمفقود")
            }


        }
    }


    private fun galleryIntent() {
        openGalleryForImage()
//        openGalleryForPhotoPicker()
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

            try {

                uri = data.data!!
                binding.selectedImage.setImageURI(uri)

            } catch (e: Exception) {
                Log.e("GalleryERROR", e.message.toString())
            }


        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            try {
                val bitMap = data.extras?.get("data") as Bitmap
                binding.selectedImage.setImageBitmap(bitMap)

            } catch (e: Exception) {

                Log.e("CameraERROR", e.message.toString())

            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun bitmapToFile(context: Context, bitmap: Bitmap): File? {
        val file = File(context.getExternalFilesDir(null), "photo.jpg")
        return try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            file
        } catch (e: IOException) {
            e.printStackTrace()
            null
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