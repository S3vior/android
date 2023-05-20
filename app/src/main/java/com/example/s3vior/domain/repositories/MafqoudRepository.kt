package com.example.s3vior.domain.repositories

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem

interface MafqoudRepository {

    suspend fun getAllPersons():  List<MafqoudModel>

    suspend fun getMatchedPersons(): List<MatchedPersonsResponseModelItem>
    suspend fun uploadPerson(
        token: String,
        name: String,
        age: Int,
        gender: String,
        type: String,
        description: String,
        latitude :String,
        longitude:String,
        imageAsByte: ByteArray,
        extension: String
    ): String

    suspend fun searchPerson(searchWord: String): List<MafqoudModel>

    suspend fun getPersonDetails(id:Int) : MafqoudModel
}