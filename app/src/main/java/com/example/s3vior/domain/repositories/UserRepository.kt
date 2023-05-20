package com.example.s3vior.domain.repositories

import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest

interface UserRepository {
    suspend fun changeUserPassword(
        token: String,
        request: PasswordChangeRequest
    ): String

    suspend fun contactUs(
        token: String,
        contactUs: ContactUs
    ):String
}