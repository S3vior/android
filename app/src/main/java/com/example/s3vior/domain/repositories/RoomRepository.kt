package com.example.s3vior.domain.repositories

import com.example.s3vior.domain.model.State
import com.example.s3vior.room.PersonEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun insertPersons(personEntity: PersonEntity)

   suspend fun getPersons(): Flow<List<PersonEntity>>
}