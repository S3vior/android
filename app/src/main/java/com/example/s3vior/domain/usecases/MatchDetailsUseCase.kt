package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.repositories.MafqoudRepository
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
import javax.inject.Inject

class MatchDetailsUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {

    suspend operator fun invoke(id: Int): State<MatchedPersonsResponseModelItem> {
        return State.Success(mafqoudRepository.getMatchDetailsById(id))
    }
}