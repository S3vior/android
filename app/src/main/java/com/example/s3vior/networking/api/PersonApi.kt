package com.example.s3vior.networking.api

import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PersonApi {
    @GET("persons")
    suspend fun getAllPersons(): Response<List<Person>>

    @POST("person")
    suspend fun sendAllPersons(
        @Header("token") token: String,
        @Body person: Person
    )

    @POST("persons")
    fun sendPersons(
        @Body userData: com.example.s3vior.domain.model.UserInfo
    ): Call<com.example.s3vior.domain.model.UserInfo>


}


