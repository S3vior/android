package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("type")
    var type: String?
)
