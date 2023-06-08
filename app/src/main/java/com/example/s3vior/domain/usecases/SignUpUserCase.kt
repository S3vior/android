package com.example.s3vior.domain.usecases

import com.example.s3vior.data.repositories.UserRepositoryImpl
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class SignUpUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        signUpFields: UserApi.SignUpFields
    ): UserRepositoryImpl.SignInResult {

        return try {

            userRepository.signUp(
                signUpFields
            )
        } catch (e: Exception) {
            UserRepositoryImpl.SignInResult(
                "something wrong", userRepository.signUp(
                    signUpFields
                ).token
            )
        }
    }
}