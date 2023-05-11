package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.repositories.MafqoudRepository
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMatchedPersonsUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {

    private var allMatchedPersons :List<MatchedPersonsResponseModelItem> = emptyList()
    suspend operator fun invoke(): Flow<State<List<MatchedPersonsResponseModelItem>>> {
        return flow {
            emit(State.Loading)
            allMatchedPersons = mafqoudRepository.getMatchedPersons()
            emit(State.Success(allMatchedPersons))
        }
    }
}