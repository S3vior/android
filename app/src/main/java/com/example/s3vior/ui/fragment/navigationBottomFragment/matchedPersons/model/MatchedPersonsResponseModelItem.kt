package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model

import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.FoundPerson
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MissedPerson

data class MatchedPersonsResponseModelItem(
    val found_person: FoundPerson,
    val missed_person: MissedPerson,
    val id:Int
)