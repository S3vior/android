package com.example.s3vior.domain.usecases

import com.example.s3vior.data.source.remote.responseModels.ScrapedPersonsResponseItem
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.repositories.MafqoudRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetScrapedPersonsUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {

    private var allPersons :List<ScrapedPersonsResponseItem> = emptyList()
    suspend operator fun invoke(): Flow<State<List<ScrapedPersonsResponseItem>>> {
        return flow {
            emit(State.Loading)

            allPersons = mafqoudRepository.getScrapedPersons()
            emit(State.Success(allPersons))
        }
    }
}