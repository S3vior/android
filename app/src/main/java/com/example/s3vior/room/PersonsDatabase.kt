package com.example.s3vior.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 1)
abstract class PersonsDatabase :RoomDatabase(){
    abstract fun getDao():Dao
}