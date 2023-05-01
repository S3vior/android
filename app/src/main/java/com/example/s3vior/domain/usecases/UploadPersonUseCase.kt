package com.example.s3vior.domain.usecases

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.s3vior.domain.repositories.MafqoudRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.internal.addHeaderLenient
import javax.inject.Inject

class UploadPersonUseCase @Inject constructor(
    var mafqoudRepository: MafqoudRepository,
    @ApplicationContext var context: Context
) {
    suspend operator fun invoke(
        token: String,
        name: String,
        age: Int,
        gender: String,
        type: String,
        description: String, imageUri: Uri
    ): String? {
        var imageAsByte =
            context.contentResolver.openInputStream(imageUri)?.buffered()?.use { it.readBytes() }

        val imageType = context.contentResolver.getType(imageUri)
        val extension = imageType!!.substring(imageType.indexOf("/") + 1)
        var x = mafqoudRepository.uploadPerson(
            token,
            name,
            age,
            gender,
            type,
            description,
            imageAsByte!!,
            extension
        )
        Log.d("uriImage", imageUri.toString())
        return x;
    }
}