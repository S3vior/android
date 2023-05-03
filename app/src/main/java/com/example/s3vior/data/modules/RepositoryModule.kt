package com.example.s3vior.data.modules

import com.example.s3vior.data.repositories.MafqoudRepositoryImpl
import com.example.s3vior.domain.repositories.MafqoudRepository
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

}