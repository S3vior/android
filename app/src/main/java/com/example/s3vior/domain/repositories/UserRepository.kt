package com.example.s3vior.domain.repositories

interface UserRepository {
    suspend fun changeUserPassword(
        token: String,
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ): String
}