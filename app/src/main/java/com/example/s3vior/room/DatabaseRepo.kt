package com.example.s3vior.room

import com.example.s3vior.data.source.local.MafqoudDao
import com.example.s3vior.domain.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseRepo @Inject constructor(private val dao: MafqoudDao) {

//    suspend fun getPersonsFromRoom(): Flow<State<List<PersonEntity>>> {
//        return flow {
//            emit(State.Success(dao.getAllPersons()))
//        }
//    }
//
//    suspend fun insertDataToRoom(personEntity: PersonEntity)=dao.insertPersons(personEntity)
}