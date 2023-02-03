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
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.s3vior.databinding.BottomSheetFragmentBinding
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.model.UserInfo
import com.example.s3vior.networking.API
import com.example.s3vior.networking.UploadStreamRequestBody
import com.example.s3vior.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
        config.put("cloud_name", "khaledelabady11")
        config.put("api_key", "772589215762873")
        config.put("api_secret", "6EtKMojSfmrBn3t2UMH2wrAODCA")
        MediaManager.init(requireActivity(), config)

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
        // For latest versions API LEVEL 19+
//        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        intent.type = "image/*"
//        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY);
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY)
    }

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
                        Log.d("ajbcjabv", "onActivityResult: $picturePath\n ${data}")
                    }
                }
            }


//            if (data.clipData != null) {
//                val count = data.clipData?.itemCount
//                for (i in 0 until count!!) {
//                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
//                    Images.add(imageUri)
//                }
//                try {
//                    _binding.imageView.setImageURI(Images[0])
//                    _binding.imageView1.setImageURI(Images[1])
//                    _binding.imageView2.setImageURI(Images[2])
//                    _binding.imageView3.setImageURI(Images[3])
//                    _binding.imageView4.setImageURI(Images[4])
//                } catch (e: Exception) {
//                    Toast.makeText(this.context, "You must select 5 photos", Toast.LENGTH_LONG)
//                        .show()
//                }
//
//            } else if (data.data != null) {
//
//                val imageUri: Uri = data.data!!
//                _binding.imageView.setImageURI(imageUri)
//
//            }

        }
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap
            _binding.imageView.setImageBitmap(bitMab)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun uploadToCloudinary(filepath: String) {
        MediaManager.get().upload(filepath).callback(object : UploadCallback {
            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                Toast.makeText(requireContext(), "Task successful", Toast.LENGTH_SHORT).show()
                Log.d("Aklbvaiugbavi", "onSuccess: ${resultData?.get("secure_url")}")
                val userInfo = UserInfo(
                    age = 21,
                    description = "كسم ميمي ",
                    id = 5, "${resultData?.get("secure_url")}", "no message yet", "كسم فرعون"
                )

                lifecycleScope.launch {
                    API.apiService.sendPersons(userInfo)
                }
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {

            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {

            }

            override fun onError(requestId: String?, error: ErrorInfo?) {

                Toast.makeText(requireContext(), "Task Not successful" + error, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onStart(requestId: String?) {

                Toast.makeText(requireContext(), "Start", Toast.LENGTH_SHORT).show()
            }
        }).dispatch()
    }

    private fun uploadFile(file: File) {
        lifecycleScope.launch {
            try {
                val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
                val filePart = MultipartBody.Part.createFormData(
                    "image",
                    "test.jpg",
                    requestBody
                )

                API.apiService.sendAllPersons("10",10,"10","aac"  ,filePart)


            } catch (e: Exception) {
                println("!!! Handle Exception $e")
            }
            // Coroutine scope from androidx.lifecycle:lifecycle-runtime-ktx

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