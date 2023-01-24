package com.example.s3vior.networking

 import com.example.s3vior.ui.recyclerView.Person
 import retrofit2.Call
 import retrofit2.Response
 import retrofit2.http.GET
 import retrofit2.http.POST

interface PersonApiService {
 @GET("persons")
  suspend fun getAllPersons(): Response<List<Person>>

 @POST("persons")
 suspend fun sendAllPersons(): Response<List<Person>>
}


