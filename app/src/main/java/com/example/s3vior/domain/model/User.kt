package com.example.s3vior.domain.model

import com.google.gson.JsonObject

data class User(
    var id: Int? = null,
    var user_name: String,
    var phone_number: String,
    var password: String,
    var token: String = ""
)

//) {
//    fun userObject(): JsonObject {
//        val userObject = JsonObject()
//        val userDetails = JsonObject()
//        userDetails.addProperty("user_name", user_name)
//        userDetails.addProperty("phone_number", phone_number)
//        userDetails.addProperty("password", password)
//        userObject.add("user", userDetails)
//        return userObject
//    }
//}