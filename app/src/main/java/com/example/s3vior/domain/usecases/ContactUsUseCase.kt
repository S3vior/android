package com.example.s3vior.domain.usecases

import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class ContactUsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        token: String,
        contactUs: ContactUs
    ): String {

        return try {

            userRepository.contactUs(
                token = token,
                contactUs
            )
        } catch (e: Exception) {
           "something wrong"
        }
    }
}