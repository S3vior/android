package com.example.s3vior.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.s3vior.room.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MafqoudDao {

    @Insert
    suspend fun insertPersons(personEntity: PersonEntity)

    @Query("select * from persons_table")
     fun getAllPersons(): Flow<List<PersonEntity>>
}