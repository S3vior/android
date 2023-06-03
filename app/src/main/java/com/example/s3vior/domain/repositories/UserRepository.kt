package com.example.s3vior.domain.repositories

import com.example.s3vior.data.repositories.UserRepositoryImpl
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.Fcm
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.data.source.remote.endPoints.UserApi

interface UserRepository {
    suspend fun changeUserPassword(
        token: String,
        request: PasswordChangeRequest
    ): String

    suspend fun signIn(
        signInFields: UserApi.SignInFields
    ): UserRepositoryImpl.SignInResult

    suspend fun contactUs(
        token: String,
        contactUs: ContactUs
    ):String

    suspend fun fcmToken(
        token: String,
        fcmToken:Fcm
    ):String
}