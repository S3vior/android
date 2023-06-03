package com.example.s3vior.domain.usecases

import com.example.s3vior.data.repositories.UserRepositoryImpl
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class SignInUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        signInFields: UserApi.SignInFields
    ): UserRepositoryImpl.SignInResult {

        return try {

            userRepository.signIn(
                signInFields
            )
        } catch (e: Exception) {
            UserRepositoryImpl.SignInResult(
                "something wrong", userRepository.signIn(
                    signInFields
                ).token
            )
        }
    }
}