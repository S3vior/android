package com.example.s3vior.data.repositories

import android.util.Log
import com.example.s3vior.data.source.remote.dataSource.UserRemoteDataSource
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) :
    UserRepository {
    override suspend fun changeUserPassword(
        token: String,
        request: PasswordChangeRequest
    ): String {
        val changePasswordResult = userRemoteDataSource.changeUserPassword(
            token,
            request
        )

        return if (changePasswordResult.isSuccessful && changePasswordResult.body() != null && changePasswordResult.code() == 200) {

            Log.d("fucking", changePasswordResult.body()!!.source().toString())
            "Password updated successfully."
        } else {
            "Failed to updated Password"
        }

    }

    override suspend fun contactUs(token: String, contactUs: ContactUs): String {


        val contactUsResult = userRemoteDataSource.contactUs(
            token, contactUs
        )
        return if (contactUsResult.isSuccessful && contactUsResult.body() != null && contactUsResult.code() == 200) {
            "problem send successfully"
        } else {
            "something error"
        }
    }


}
