package com.example.s3vior.repository

import android.annotation.SuppressLint
import android.net.Uri
import android.os.FileUtils
import com.example.s3vior.networking.API
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddCaseRepository{

    @SuppressLint("SuspiciousIndentation")
   suspend fun uploadProduct(
        person_name: String,
        person_age: String,
        person_des: String,
        file: File
    ){


        val personNameRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), person_name)
        val personDescriptionRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), person_des)
        val personAgeRequestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"), person_age)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)

        val filePart = MultipartBody.Part.createFormData(
            "Image",
            file.name,
            requestBody
        )

         API.apiService.sendPerson(
            personNameRequestBody,
            personDescriptionRequestBody,
            personAgeRequestBody,
            filePart
        )



    }

}