package com.example.s3vior.networking

 import com.example.s3vior.ui.recyclerView.Person
 import com.google.gson.JsonObject
 import retrofit2.Call
 import retrofit2.http.Body
 import retrofit2.http.GET
 import retrofit2.http.POST

interface PersonApiService {
 @GET("persons")
  fun getAllPersons(): Call<List<Person>>

 @POST("persons")
 fun insertPerson(@Body person: JsonObject): Call<List<Person>>
}


