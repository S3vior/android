package com.example.s3vior.networking

 import com.example.s3vior.ui.recyclerView.Person
 import retrofit2.Call
 import retrofit2.http.GET

interface PersonApiService {
 @GET("persons")
  fun getAllPersons(): Call<List<Person>>
}


