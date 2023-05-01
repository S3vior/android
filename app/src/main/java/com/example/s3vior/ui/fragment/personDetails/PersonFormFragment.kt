package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.networking.API
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody


class PersonFormFragment : BaseFragment<FragmentPersonFormBinding>(
    FragmentPersonFormBinding::inflate
) {
    private val sharedViewModel: SharedViewModel by activityViewModels()


    lateinit var status: String

    @SuppressLint("UseRequireInsteadOfGet", "SetTextI18n")

    // val status = args.maqoudStatus
    private fun observeDataFromViewModel() {

        sharedViewModel.firstDetails.observe(viewLifecycleOwner) {
            binding.personName.text = it.name
            binding.personDescription.text = it.description

        }

        sharedViewModel.mafqoudStatus.observe(viewLifecycleOwner) {
            status = it
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
            val imageUri = sharedViewModel.thirdDetails.value?.imageUri

            val imageAsByte = context?.contentResolver?.openInputStream(imageUri!!)?.buffered()
                ?.use { it.readBytes() }
            val imageType = context?.contentResolver?.getType(imageUri!!)
            val extension = imageType!!.substring(imageType.indexOf("/") + 1)

            lifecycleScope.launch(Dispatchers.IO) {
                sharedViewModel.uploadPersonUseCase(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4MjkzNDUxNSwianRpIjoiZjFiOTcwNDgtZTNlMy00NzhhLThlNjYtNTFkMjA2NGE5YjgwIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjgyOTM0NTE1LCJleHAiOjE2ODI5NTI1MTV9.cC_Cop872cwxCrBnllDXJhG9V2NuwpB9uEUwt33rVsc",
                    name = binding.personName.text.toString(),
                    age = binding.personAge.text.toString().toInt() ,
                    gender = binding.personGender.text.toString(),
                    description = binding.personDescription.text.toString(),
                    type = binding.personGender.text.toString(),
                    imageUri = imageUri!!
                )
//                val uploadImageResult = API.apiService.upLoadPerson(
//
//                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4Mjg2MjIwMywianRpIjoiYTMxNTNhNDItNDVmMS00ZTdlLTlhYjUtYTQ2MjZhMGEyOWY0IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjgyODYyMjAzLCJleHAiOjE2ODI4ODAyMDN9.Zetpd0krB14G-NE82lMZ_NLf5BCMvhjypENyTV7yMKM",
//                    name = binding.personName.text.toString()
//                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                    age = binding.personAge.text.toString()
//                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                    gender = binding.personGender.text.toString()
//                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                    description = binding.personDescription.text.toString()
//                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                    type = binding.personGender.text.toString()
//                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                    image = MultipartBody.Part.createFormData(
//                        "image",
//                        "image.$extension",
//                        imageAsByte!!.toRequestBody("*/*".toMediaType())
//                    )
//
//                )
//                if (uploadImageResult.isSuccessful) {
//                    lifecycleScope.launch(Dispatchers.Main) {
//
//                        Toast.makeText(
//                            context,
//                            uploadImageResult.body()!!.message,
//                            Toast.LENGTH_LONG
//                        ).show()
//
                //                 }
                //             }
            }

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

//    image = "${resultData?.get("secure_url")}",
//    name = binding.personName.text.toString(),
//    age = binding.personAge.text.toString().toInt(),
//    gender = binding.personGender.text.toString(),
//    description = binding.personDescription.text.toString(),
//    type = binding.personGender.text.toString(),


//    private fun uploadFile(file: File) {
//        lifecycleScope.launch {
//            try {
//                val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
//                val filePart = MultipartBody.Part.createFormData(
//                    "image",
//                    "test.jpg",
//                    requestBody
//                )
//            } catch (e: Exception) {
//                println("!!! Handle Exception $e")
//            }
//
//        }
//    }

}