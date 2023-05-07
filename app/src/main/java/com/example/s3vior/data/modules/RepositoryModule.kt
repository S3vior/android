package com.example.s3vior.data.modules

import com.example.s3vior.data.repositories.MafqoudRepositoryImpl
import com.example.s3vior.data.repositories.RoomRepositoryImpl
import com.example.s3vior.data.source.local.MafqoudDao
import com.example.s3vior.data.source.local.MafqoudDatabase
import com.example.s3vior.domain.repositories.MafqoudRepository
import com.example.s3vior.domain.repositories.RoomRepository
import com.example.s3vior.domain.usecases.RoomGetPersonsUseCase
import com.example.s3vior.domain.usecases.RoomInsertPersonUseCase
import com.example.s3vior.domain.usecases.RoomUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMafqoudRepository(mafqoudRepositoryImpl: MafqoudRepositoryImpl): MafqoudRepository {
        return mafqoudRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideRoomRepositoryImpl(dao: MafqoudDao): RoomRepositoryImpl {
        return RoomRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(RoomRepositoryImpl: RoomRepositoryImpl): RoomRepository {
        return RoomRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideRoomUseCases(repository: RoomRepository): RoomUseCases {
        return RoomUseCases(
            roomGetPersonsUseCase = RoomGetPersonsUseCase(repository),
            roomInsertPersonUseCase = RoomInsertPersonUseCase(repository)
        )
    }

}