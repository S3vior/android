package com.example.s3vior.networking

import com.example.s3vior.domain.model.UserInfo
import com.example.s3vior.domain.model.UserInfo2
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PersonApiService {
    @GET("persons")
    suspend fun getAllPersons(): Response<List<Person>>


    @Multipart
    @POST("persons")
    suspend fun sendAllPersons(
        @Query("name") name: String,
        @Query("age") age: Int,
        @Query("description") description: String,
        @Query("message") message: String,
        @Part image: MultipartBody.Part,
    )

    @POST("persons")
    fun sendPersons(
        @Body userData: UserInfo
    ): Call<UserInfo>


}


