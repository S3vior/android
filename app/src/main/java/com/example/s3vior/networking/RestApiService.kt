package com.example.s3vior.networking

import com.example.s3vior.domain.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {

    fun addUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = API.apiService
        retrofit.sendPersons(userData)
            .enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}