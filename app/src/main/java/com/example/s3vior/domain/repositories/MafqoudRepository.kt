package com.example.s3vior.domain.repositories

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State

interface MafqoudRepository {

    suspend fun getAllPersons():  List<MafqoudModel>

    suspend fun uploadPerson(
        token: String,
        name: String,
        age: Int,
        gender: String,
        type: String,
        description: String,
        imageAsByte: ByteArray,
        extension: String
    ): String

    suspend fun searchPerson(searchWord: String): List<MafqoudModel>
}