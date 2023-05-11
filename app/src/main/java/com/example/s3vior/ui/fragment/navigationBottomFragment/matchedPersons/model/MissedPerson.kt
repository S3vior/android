package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model

data class MissedPerson(
    val age: String,
    val created_at: String,
    val description: String,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val type: String
)