package com.example.s3vior.domain.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("age") val age: Int,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("message") val message: String,
    @SerializedName("name") val name: String
)
//data class UserInfo2(
//    @SerializedName("age") val age: Int,
//    @SerializedName("description") val description: String,
//    @SerializedName("id") val id: Int,
//    @SerializedName("message") val message: String,
//    @SerializedName("name") val name: String,
//)

