package com.example.s3vior.data.repositories

import com.example.s3vior.data.source.local.MafqoudDao
import com.example.s3vior.domain.repositories.RoomRepository
import com.example.s3vior.room.PersonEntity
import kotlinx.coroutines.flow.Flow

class RoomRepositoryImpl(
    private val dao: MafqoudDao
) : RoomRepository {

    override suspend fun insertPersons(personEntity: PersonEntity) =
        dao.insertPersons(personEntity = personEntity)

    override suspend fun getPersons(): Flow<List<PersonEntity>> =
        dao.getAllPersons()
}