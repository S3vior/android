package com.example.s3vior.networking

import com.example.s3vior.ui.recyclerView.Person
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PersonApiService {
    @GET("persons")
    suspend fun getAllPersons(): Response<List<Person>>

    @Multipart
    @POST("persons")
    suspend fun uploadCase(

    ): Response<List<Person>>
}


