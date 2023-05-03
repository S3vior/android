package com.example.s3vior.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Insert
    suspend fun insertPersons(personEntity: PersonEntity)

    @Query("select * from persons_table")
    suspend fun getAllPersons():List<PersonEntity>
}