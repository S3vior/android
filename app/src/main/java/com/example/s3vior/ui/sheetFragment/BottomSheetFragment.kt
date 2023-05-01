package com.example.s3vior.ui.sheetFragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.example.s3vior.databinding.BottomSheetFragmentBinding
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.domain.model.UserInfo
import com.example.s3vior.networking.UploadStreamRequestBody
import com.example.s3vior.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File

class BottomSheetFragment(private val _binding: FragmentPersonDetailsBinding) :
    BottomSheetDialogFragment() {

    var config: HashMap<String, String> = HashMap()


    private lateinit var binding: BottomSheetFragmentBinding
    var Images: ArrayList<Uri> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFragmentBinding.inflate(inflater)
        initButtons()

        return binding.root
    }

    private fun initButtons() {
        binding.linearGallery.setOnClickListener { galleryIntent() }
        binding.linearCamera.setOnClickListener { cameraIntent() }
    }

    private fun galleryIntent() {
        openGalleryForImage()
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

    @Deprecated("Deprecated in Java")
    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {


            data.data?.let { uri ->
                context?.contentResolver?.query(uri, null, null, null, null)?.use {
                    if (it.moveToFirst()) {
                        val picturePath =
                            it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
                         uploadFile(File(picturePath))
                        var imageAsByte =
                           requireActivity().contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }


                     //   uploadFile(uri)

                        // uploadToCloudinary(picturePath)
                        Log.d("ajbcjabv", "onActivityResult: $picturePath")
                    }
                }
            }

        }
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap
            _binding.imageView.setImageBitmap(bitMab)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



    private fun uploadFile(file: File) {
        lifecycleScope.launch {
            try {
            //    val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    "test.jpg",
             //       requestBody
                )

            //    API.apiService.sendAllPersons("10",10,"10"  ,filePart)


            } catch (e: Exception) {
                println("!!! Handle Exception $e")
            }

        }
    }

    private fun uploadFile(uri: Uri) {
        lifecycleScope.launch {
            val stream = requireActivity().contentResolver.openInputStream(uri) ?: return@launch
            val request = UploadStreamRequestBody("image/*", stream, onUploadProgress = {
                Log.d("MyActivity", "On upload progress $it")

            })
            val filePart = MultipartBody.Part.createFormData(
                "image",
                "test.jpg",
                request
            )
            Log.d("acacacMyaAactivity", filePart .toString())
            try {
            //    API.apiService.sendAllPersons( "10",10,"10","aac"  ,filePart)
            }
            catch(e: Exception) { // if something happens to the network
                Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                return@launch
            }
            Log.d("MyActivity", "On finish upload file")
        }}
}