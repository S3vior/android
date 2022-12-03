package com.example.s3vior.ui.sheetFragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s3vior.databinding.BottomSheetFragmentBinding
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(private val _binding: FragmentPersonDetailsBinding) :
    BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFragmentBinding

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
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {


            _binding.imageView.setImageURI(data.data)
        }
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap
            _binding.imageView.setImageBitmap(bitMab)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}