package com.example.s3vior.data.source.remote.endPoints

import com.example.s3vior.domain.model.Profile
import com.example.s3vior.domain.model.User
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface UserApi {
    @POST("auth/login")
    suspend fun signIn(@Body user: SignInFields): Response<SignInToken>

    data class SignInFields(
        @SerializedName("user_name")
        val username: String,
        @SerializedName("password")
        val password: String
    )
    data class SignInToken(
        @SerializedName("access_token")
        val token: String)

    @POST("auth/register")
    suspend fun signUp(@Body user: SignUpFields):  Response<SignInToken>

    data class SignUpFields(
        @SerializedName("user_name")
        val username: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("phone_number")
        val phoneNumber: String,
    )

    @GET("users/profile")
    fun userProfile(@Header("TOKEN") token: String): Call<Profile>

    @POST("change_password")
    suspend fun changeUserPassword(
        @Header("token") token: String,
        @Body request: PasswordChangeRequest
    ): Response<ResponseBody>


    @POST("contact_us")
    suspend fun contactUs(
        @Header("token") token: String,
        @Body request: ContactUs
    ): Response<ResponseBody>

    @POST("users/update_token")
    suspend fun sendFcmToken(
        @Header("token") token: String,
        @Body fcm: Fcm
    ): Response<ResponseBody>



}

data class Fcm(
    @SerializedName("fcm_token")
    val fcmToken: String
)

data class ContactUs(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("problem")
    val problem: String

)

data class PasswordChangeRequest(
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("confirm_password")
    val confirmPassword: String
)