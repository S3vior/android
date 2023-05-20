package com.example.s3vior.data.source.remote.dataSource

import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.data.source.remote.endPoints.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private var userApi: UserApi) {

    suspend fun changeUserPassword(
        token: String,
         request: PasswordChangeRequest
    ) : Response<ResponseBody> = withContext(Dispatchers.IO){
        userApi.changeUserPassword(
            token = token,
            request
        )
    }

    suspend fun contactUs(
        token: String,
        request: ContactUs
    ) = withContext(Dispatchers.IO){
        userApi.contactUs(token = token,request = request)
    }
}