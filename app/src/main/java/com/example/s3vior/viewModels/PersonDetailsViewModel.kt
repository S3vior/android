package com.example.s3vior.viewModels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.s3vior.repository.AddCaseRepository
import java.io.File

class PersonDetailsViewModel(context: Context) : ViewModel() {

    private val addCaseRepository = AddCaseRepository(context)

    suspend fun upload(
        person_name: String,
        person_age: String,
        person_des: String,
        file: Uri
    ) {

        addCaseRepository.uploadProduct(
            person_name,
            person_age,
            person_des,
            file,
        )
    }
}