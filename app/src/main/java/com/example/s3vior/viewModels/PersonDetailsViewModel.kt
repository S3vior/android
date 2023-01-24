package com.example.s3vior.viewModels

import androidx.lifecycle.ViewModel
import com.example.s3vior.repository.AddCaseRepository
import java.io.File

class PersonDetailsViewModel : ViewModel() {

    private val addCaseRepository = AddCaseRepository()

    suspend fun upload(
        person_name: String,
        person_age: String,
        person_des: String,
        file: File
    ) {

        addCaseRepository.uploadProduct(
            person_name,
            person_age,
            person_des,
            file,
        )
    }
}