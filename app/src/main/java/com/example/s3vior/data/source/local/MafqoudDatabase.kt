package com.example.s3vior.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.s3vior.room.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class MafqoudDatabase : RoomDatabase() {
    abstract fun getDao(): MafqoudDao
}