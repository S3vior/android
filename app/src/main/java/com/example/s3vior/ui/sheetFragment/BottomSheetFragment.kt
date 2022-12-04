package com.example.s3vior.ui.sheetFragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.s3vior.databinding.BottomSheetFragmentBinding
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(private val _binding: FragmentPersonDetailsBinding) :
    BottomSheetDialogFragment() {

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
        binding.galleryIntent.setOnClickListener { galleryIntent() }
        binding.cameraIntent.setOnClickListener { cameraIntent() }
    }

    private fun galleryIntent() {
        openGalleryForImage()
    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_CAMERA)
    }

    private fun openGalleryForImage() {
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures"),
                Constants.UploadImage.REQUEST_CODE_GALLERY
            )
        } else { // For latest versions API LEVEL 19+
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY);
        }
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {

            if (data.clipData != null){
                val count = data.clipData?.itemCount
                for (i in 0 until count!!){
                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    Images.add(imageUri)
                }
                try {
                    _binding.imageView.setImageURI(Images[0])
                    _binding.imageView1.setImageURI(Images[1])
                    _binding.imageView2.setImageURI(Images[2])
                    _binding.imageView3.setImageURI(Images[3])
                    _binding.imageView4.setImageURI(Images[4])
                }catch (e:Exception){
                    Toast.makeText(this.context,"You must select 5 photos",Toast.LENGTH_LONG).show()
                }

            }
            else if (data.data != null) {

                val imageUri: Uri = data.data!!
                _binding.imageView.setImageURI(imageUri)

            }

        }
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap
            _binding.imageView.setImageBitmap(bitMab)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}