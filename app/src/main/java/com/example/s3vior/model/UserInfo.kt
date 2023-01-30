package com.example.s3vior.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part

data class UserInfo(
    @SerializedName("age") val age: Int,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String
)
data class UserInfo2(
    @SerializedName("age") val age: Int,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String,
)

