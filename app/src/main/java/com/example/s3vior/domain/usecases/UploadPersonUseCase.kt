package com.example.s3vior.domain.usecases

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.s3vior.domain.repositories.MafqoudRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UploadPersonUseCase @Inject constructor(
    private var mafqoudRepository: MafqoudRepository,
    @ApplicationContext var context: Context
) {
    suspend operator fun invoke(
        token: String,
        name: String,
        age: Int,
        gender: String,
        type: String,
        description: String,
        latitude:String,
        longitude:String,
        imageUri: Uri
    ): String {
        val imageAsByte =
            context.contentResolver.openInputStream(imageUri)?.buffered()?.use { it.readBytes() }

        val imageType = context.contentResolver.getType(imageUri)
        val extension = imageType!!.substring(imageType.indexOf("/") + 1)
       return try {

            val x = mafqoudRepository.uploadPerson(
                token =   token,
                name =name,
                age = age,
                gender = gender,
                type = type,
                description = description,
                latitude = latitude,
                longitude = longitude,
                imageAsByte = imageAsByte!!,
                extension = extension
            )
            Log.d("uriImage", imageUri.toString())
            return x
        } catch (e:Exception){
           "something wrong"
        }
    }
}