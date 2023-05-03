package com.example.s3vior.networking.api

import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MafqoudApi {
    @GET("persons")
    suspend fun getAllPerson(): Response<List<Person>>


    @POST("person")
    suspend fun sendAllPersons(
        @Header("token") token: String,
        @Body person: Person
    )

    @POST("persons")
    fun sendPersons(
        @Body userData: com.example.s3vior.domain.model.UserInfo
    ): Call<com.example.s3vior.domain.model.UserInfo>

    @GET("persons/search")
    suspend fun searchForPerson(
        @Query("name") name: String?
    ): Response<List<Person>>


    @POST("persons")
    @Multipart
    suspend fun upLoadPerson(
        @Header("token") token: String,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("type") type: RequestBody,
        @Part("description") description:RequestBody,
        @Part image: MultipartBody.Part
    ): Response<BaseApiResponse<String>>

}


