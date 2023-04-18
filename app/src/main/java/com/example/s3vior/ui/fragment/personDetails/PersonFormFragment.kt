package com.example.s3vior.ui.fragment.personDetails

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.networking.API
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import com.example.s3vior.viewModel.SharedViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class PersonFormFragment : BaseFragment<FragmentPersonFormBinding>(
    FragmentPersonFormBinding::inflate
) {
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private fun observeDataFromViewModel() {

        sharedViewModel.firstDetails.observe(viewLifecycleOwner) {
            binding.personName.text = it.name
            binding.personDescription.text = it.description
        }

        sharedViewModel.secondDetails.observe(viewLifecycleOwner) {
            binding.dateFindPerson.text = "${it.day}/${it.month}/${it.year}"
            binding.personAge.text = it.age
            binding.personGender.text = it.gender
        }

        sharedViewModel.thirdDetails.observe(viewLifecycleOwner) {
            binding.shapeableImageView.setImageURI(it.imageUri)
        }


    }

    private fun sendPersonData() {

        try {
            val picturePath = sharedViewModel.thirdDetails.value?.imageUri
         //   uploadFile(File(picturePath?.path!!))

            lifecycleScope.launch {
            API.apiService.sendAllPersons(
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY3OTc5MTY0MSwianRpIjoiZTY2NGQzN2UtYmE0Mi00Nzg1LTg5M2EtZWM5N2M3MTg4MjI2IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6NywibmJmIjoxNjc5NzkxNjQxLCJleHAiOjE2Nzk3OTI1NDF9.U_pBc1osOsnQl0I61tSbGjZbpDeHDUuXHle3K6SQP9k",
                Person(
                    image = picturePath.toString()+".png",
                    name =  binding.personName.text.toString(),
                    age =  binding.personAge.text.toString().toInt(),
                    gender =  binding.personGender.text.toString() ,
                    description =  binding.personDescription.text.toString() ,
                    type = binding.personGender.text.toString(),

                    )
            )}
            Log.d("Ajvbjav", "sendPersonData:  $picturePath")
            Log.d("Ajvbjav", "sendPersonData:  ${picturePath?.path}")
         } catch (e: Exception) {
           Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()

        }


    }

    override fun callFunctions() {
        observeDataFromViewModel()
        binding.button4.setOnClickListener {
            sendPersonData()
        }
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





            } catch (e: Exception) {
                println("!!! Handle Exception $e")
            }

        }
    }

}