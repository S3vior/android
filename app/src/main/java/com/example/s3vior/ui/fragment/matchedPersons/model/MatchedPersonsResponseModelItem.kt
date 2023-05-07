package com.example.s3vior.ui.fragment.matchedPersons.model

import com.example.s3vior.ui.fragment.matchedPersons.model.FoundPerson
import com.example.s3vior.ui.fragment.matchedPersons.model.MissedPerson

data class MatchedPersonsResponseModelItem(
    val found_person: FoundPerson,
    val missed_person: MissedPerson
)