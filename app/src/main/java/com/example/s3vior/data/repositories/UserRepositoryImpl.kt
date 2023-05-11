package com.example.s3vior.data.repositories

import android.util.Log
import com.example.s3vior.data.source.remote.dataSource.UserRemoteDataSource
import com.example.s3vior.domain.repositories.UserRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) :
    UserRepository {
    override suspend fun changeUserPassword(
        token: String,
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ): String {
        val changePasswordResult = userRemoteDataSource.changeUserPassword(
            token,
            oldPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            newPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            confirmPassword.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        )
        return if (changePasswordResult.code() != 200) {
            Log.d("fucking", changePasswordResult.body().toString())

            changePasswordResult.body().toString()
        } else {
            "Password updated successfully."
        }

    }
}