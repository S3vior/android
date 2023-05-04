package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.repositories.RoomRepository
import com.example.s3vior.room.PersonEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomGetPersonsUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(): Flow <List<PersonEntity>> {

        return flow {
             roomRepository.getPersons()
        }

    }

}