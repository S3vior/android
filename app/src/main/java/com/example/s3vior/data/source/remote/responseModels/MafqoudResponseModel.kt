package com.example.s3vior.data.source.remote.responseModels

import com.example.s3vior.domain.model.Location
import com.example.s3vior.domain.model.MafqoudModel


data class MafqoudResponseModel(
    val id: Int?,
    val name: String?,
    val age: String?,
    val gender: String?,
    val description: String?,
    val image: String?,
    val type: String?,
    val created_at: String?,
    val location: Location
) {
    fun toMafqoudModel() = MafqoudModel(
        image = image,
        name = name,
        age = age,
        gender = gender,
        description = description,
        type = type,
        createdAt = created_at,
        id = id,
        location = Location(
            latitude = location.latitude,
            longitude = location.longitude,
            address = location.address
        )


    )
}

