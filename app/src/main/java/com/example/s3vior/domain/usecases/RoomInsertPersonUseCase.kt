package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.repositories.RoomRepository
import com.example.s3vior.room.PersonEntity
import javax.inject.Inject

class RoomInsertPersonUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    suspend operator fun invoke(personEntity: PersonEntity) =
        roomRepository.insertPersons(personEntity)
}