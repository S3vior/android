package com.example.s3vior.repository

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.FileUtils
import android.util.Log
import android.widget.Toast
import com.example.s3vior.model.Person
import com.example.s3vior.networking.API
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AddCaseRepository(val ctx:Context){

    @SuppressLint("SuspiciousIndentation")
   suspend fun uploadProduct(
        person_name: String,
        person_age: String,
        person_des: String,
        file: Uri
    ){


        val fileToSend = prepareFilePart("image",Uri.parse("/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images/IMG-20230125-WA0094.jpeg"))
        val personNameRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "person_name")
        val personDescriptionRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "person_des")
        val personAgeRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "person_age")
       // val requestBody = RequestBody.create(MediaType.parse("image/*"), file)

//        val filePart = MultipartBody.Part.createFormData(
//            "Image",
//            file.name,
//            requestBody
//        )

         API.apiService.sendPerson(
            personNameRequestBody,
            personDescriptionRequestBody,
            personAgeRequestBody,
             fileToSend
        ).enqueue(object :Callback<Person>{
             override fun onResponse(call: Call<Person>, response: Response<Person>) {

             }

             override fun onFailure(call: Call<Person>, t: Throwable) {

             }

         })

//        Log.d("ascbaTAG", "uploadProduct: ${result.code()}")
//            Toast.makeText(ctx, "success", result.code()).show()



    }
    private fun prepareFilePart(partName: String,  fileUri: Uri): MultipartBody.Part {
        val file: File = com.example.s3vior.utils.FileUtils.getFile(ctx, fileUri)
        val requestFile: RequestBody = RequestBody.create(
            MediaType.parse(ctx.contentResolver.getType(fileUri)!!), file)
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }
}