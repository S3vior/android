package com.example.s3vior.data.modules

import android.content.Context
import androidx.room.Room
import com.example.s3vior.room.PersonEntity
import com.example.s3vior.data.source.local.MafqoudDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MafqoudDatabase::class.java,

        "persons_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: MafqoudDatabase) = db.getDao()
 

    @Provides
    @Singleton
    fun provideEntity() = PersonEntity()
}