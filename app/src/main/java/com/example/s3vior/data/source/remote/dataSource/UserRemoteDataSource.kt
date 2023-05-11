package com.example.s3vior.data.source.remote.dataSource

import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.networking.api.BaseApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private var userApi: UserApi) {

    suspend fun changeUserPassword(
        token: String,
        oldPassword: RequestBody,
        newPassword: RequestBody,
        confirmPassword: RequestBody
    ) :Response<String> = withContext(Dispatchers.IO){
        userApi.changeUserPassword(
            token = token,
            oldPassword = oldPassword,
            newPassword = newPassword,
            confirmPassword = confirmPassword
        )
    }
}