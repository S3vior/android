package com.example.s3vior.networking

 import com.example.s3vior.model.Person
 import okhttp3.MultipartBody
 import okhttp3.RequestBody
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
 suspend fun sendPerson(
      @Part("Name") personName :RequestBody?,
      @Part("Age") personAge :RequestBody?,
      @Part("Description") personDescription :RequestBody?,
      @Part file:MultipartBody.Part?,
 ): Response<List<Person>>
}


