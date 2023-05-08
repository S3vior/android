package com.example.s3vior.data.source.remote.endPoints

import com.example.s3vior.domain.model.Profile
import com.example.s3vior.domain.model.User
import com.example.s3vior.networking.api.BaseApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("auth/login")
    fun signIn(@Body user: User): Call<User>

    @POST("auth/register")
    fun signUp(@Body user: User): Call<User>

    @GET("users/profile")
    fun userProfile(@Header("TOKEN") token: String): Call<Profile>

    @POST("change_password")
   suspend fun changeUserPassword(
        @Header("token") token: String,
        @Query("old_password") oldPassword: String,
        @Query("new_password") newPassword: String,
        @Query("confirm_password") confirmPassword: String,
    ): Response<String>

}