package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.repositories.MafqoudRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPersonsUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {

    private var allPersons :List<MafqoudModel> = emptyList()
    suspend operator fun invoke(): Flow<List<MafqoudModel>> {
        return flow {
             allPersons = mafqoudRepository.getAllPersons()
           // emit(State.Loading)
          //  delay(500)
            emit(allPersons)
        }
    }
}