package com.example.s3vior.data.source.remote.responseModels

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import kotlinx.serialization.Serializable


data class MafqoudResponseModel(
    val id: Int,
    val name: String,
    val age: Int,
    val gender: String,
    val description: String,
    val image: String,
    val type: String,
    val created_at:String
) {
    fun toMafqoudModel() = MafqoudModel(
        image = image,
        name = name,
        age = age,
        gender = gender,
        description = description,
        type = type
    )
}

