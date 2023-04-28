package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.util.Log
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

    var config: HashMap<String, String> = HashMap()
    lateinit var status: String

    @SuppressLint("UseRequireInsteadOfGet")

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

            Log.d("ndvkbaibvib", "sendPersonData: $status")

            val imageAsByte = context?.contentResolver?.openInputStream(imageUri!!)?.buffered()
                ?.use { it.readBytes() }
            val imageType = context?.contentResolver?.getType(imageUri!!)
            val extension = imageType!!.substring(imageType.indexOf("/") + 1)
            Log.d("uploadImageUri", imageUri.toString())
            Log.d("uploadimageAsByte", imageAsByte.toString())
            Log.d("uploadimageAsByte", extension)



            lifecycleScope.launch(Dispatchers.IO) {
                val uploadImageResult = API.apiService.upLoadPerson(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4MjY5NjM3MiwianRpIjoiMDhkZGE0M2ItN2QwNS00M2YyLWI4OGQtY2ViN2M3M2Q2Y2YxIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjgyNjk2MzcyLCJleHAiOjE2ODI3MTQzNzJ9.JR1wu0BM88KEwa1X1jx2yWu9hZ2k_gf4A3_aDbGzlrU",
                    name = "binding.personName.text.toString()".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                    age = "binding.personAge.text.toString()".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                    gender = "binding.personGender.text.toString()".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                    description = "binding.personDescription.text.toString()".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                    type = binding.personGender.text.toString()
                        .toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                    image = MultipartBody.Part.createFormData(
                        "image",
                        "image.$extension",
                        imageAsByte!!.toRequestBody("*/*".toMediaType())
                    )
                )
                if (uploadImageResult.isSuccessful){
                    lifecycleScope.launch (Dispatchers.Main){

                        Toast.makeText(context,  uploadImageResult.body()!!.message,Toast.LENGTH_LONG).show()
                        Log.d("uploadImageSuccess",uploadImageResult.body()!!.message  )
                    }
                }
//                Log.d("uploadImageDefault",uploadImageResult.body()!!.data!!.toString())

//                  if (uploadImageResult.isSuccessful && uploadImageResult.body()?.data != null && uploadImageResult.code() == 200) {
//                    uploadImageResult.body()!!.data!!
//                } else {
//                    val errorBody = JSONObject(uploadImageResult.errorBody()!!.string())
//                    throw Exception(errorBody.getString("message"))
//
//                }
//
//                when (status) {
//                    MafqoudStatus.FOUNDED_PERSON.toString() -> {
//                        API.apiService.sendAllPersons(
//                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4MjMwMjIxNCwianRpIjoiZjNjY2RhYzctM2FhMy00MDdmLTk4MjMtYmRjZWUyMzYyZTUzIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6NCwibmJmIjoxNjgyMzAyMjE0LCJleHAiOjE2ODIzMDMxMTR9.xGxsFxhRibdatUT5WKKIuAgyrakbrENaeK46-8GbJvQ",
//                            Person(
//                                image = imageUri.toString() + ".png",
//                                name = binding.personName.text.toString(),
//                                age = binding.personAge.text.toString().toInt(),
//                                gender = binding.personGender.text.toString(),
//                                description = binding.personDescription.text.toString(),
//                                type = binding.personGender.text.toString(),
//                            )
//                        )
//                    }
//
//                    MafqoudStatus.MISSED_PERSON.toString() -> {
//                        // new fun for backend
//                    }
//                }


            }
            Log.d("Ajvbjav", "sendPersonData:  $imageUri")
            //    Log.d("Ajvbjav", "sendPersonData:  ${picturePath?.path}")
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