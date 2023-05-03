package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.repositories.MafqoudRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPersonUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {
    private var searchResult :List<MafqoudModel> = emptyList()

    suspend operator fun invoke(searchWord: String): Flow<List<MafqoudModel>> {
        return flow {
            searchResult = mafqoudRepository.searchPerson(searchWord)
            // emit(State.Loading)
            //  delay(500)
            emit(searchResult)
        }
    }
}