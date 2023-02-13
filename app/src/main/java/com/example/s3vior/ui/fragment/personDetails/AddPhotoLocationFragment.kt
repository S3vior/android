package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddPhotoLocationBinding
import com.example.s3vior.domain.model.UserInfo
import com.example.s3vior.networking.API
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.Constants
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.ThirdDetails
import kotlinx.coroutines.launch


class AddPhotoLocationFragment : BaseFragment<FragmentAddPhotoLocationBinding>(
    FragmentAddPhotoLocationBinding::inflate
) {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var uri: Uri

    override fun callFunctions() {
        initButtons()
        callBack()
    }

    private fun initButtons() {
        binding.photoFromGallery.setOnClickListener { galleryIntent() }
        binding.photoFromCamera.setOnClickListener { cameraIntent() }
    }
    private fun callBack(){
        binding.next.setOnClickListener {
            try {
                sharedViewModel.getDataFromGalleryFragment(ThirdDetails(uri))
                Navigation.findNavController(it).navigate(R.id.action_addPhotoLocationFragment_to_personFormFragment)

            }catch (e:Exception){
                Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
            }


        }
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

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {


                uri =  data.data!!


//            data.data?.let { uri ->
//                context?.contentResolver?.query(uri, null, null, null, null)?.use {
//                    if (it.moveToFirst()) {
//                        val picturePath =
//                            it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
//
//                        var imageAsByte =
//                            requireActivity().contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
//
//
//                        Toast.makeText(context,"$uri",Toast.LENGTH_SHORT).show()
//
//                        // uploadToCloudinary(picturePath)
//                        Log.d("ajbcjabv", "onActivityResult: $picturePath\n ${data}")
//                    }
//                }
//            }

        }
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap

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

}