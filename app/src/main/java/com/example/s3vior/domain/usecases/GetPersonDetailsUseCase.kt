package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.repositories.MafqoudRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(private val mafqoudRepository: MafqoudRepository) {
    suspend operator fun invoke(id: Int):  State<MafqoudModel>  {
        return State.Success(mafqoudRepository.getPersonDetails(id))
    }
}