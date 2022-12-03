package com.example.s3vior.ui.fragment.personDetails

import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.utils.Constants
import java.io.File


class PersonDetailsFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    var vFilename: String = ""
    private lateinit var binding: FragmentPersonDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(inflater)


        startFragment()
        binding.addPhoto.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent,8)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode ==  RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE && data!=null) {
            val bitMab =data.extras?.get("data") as Bitmap
            Toast.makeText(activity?.applicationContext,"success",Toast
                .LENGTH_LONG).show()
          //   binding.imageView.setImageURI(data.data) // handle chosen image
             binding.imageView.setImageBitmap(bitMab)
        }else{
           Toast.makeText(activity?.applicationContext,"error",Toast
               .LENGTH_LONG).show()
        }
    }

    private fun startFragment() {
        spinner()

    }



//    private fun dispatchTakePictureIntent() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//       try {
//           startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
//       }catch (e:Exception){
//           Toast.makeText(this.context,"error",Toast.LENGTH_LONG).show()
//       }
//
//
//    }

    private fun spinner() {
        initSpinner(Constants.SpinnerData.statuts, binding.spinnerGender)
        initSpinner(Constants.SpinnerData.age, binding.ageSpinner)
    }

    private fun initSpinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    //    private fun openGalleryForImage() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, 1)
//    }
//

//
//
//    private fun openCamera(){
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//        startActivityForResult(intent, 1)
//    }


}