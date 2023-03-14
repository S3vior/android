package com.example.s3vior.networking.api

import com.example.s3vior.domain.model.Profile
import com.example.s3vior.domain.model.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("auth/login")
    fun signIn(@Body user: User): Call<User>

    @POST("auth/register")
    fun signUp(@Body user: User): Call<User>

    @GET("users/profile")
    fun userProfile(@Header("TOKEN") token: String): Call<Profile>

}