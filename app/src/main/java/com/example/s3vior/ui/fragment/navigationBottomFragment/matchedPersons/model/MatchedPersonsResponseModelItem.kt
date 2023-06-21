package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model

data class MatchedPersonsResponseModelItem(
    val found_person: FoundPerson,
    val missed_person: MissedPerson,
    val id:Int
)