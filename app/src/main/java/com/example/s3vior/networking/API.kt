package com.example.s3vior.networking

import com.example.s3vior.data.source.remote.endPoints.MafqoudApiService
import com.example.s3vior.networking.api.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {
    private val mOkHttpClient = OkHttpClient
        .Builder()
        .callTimeout(10, TimeUnit.MINUTES)
        .connectTimeout(10, TimeUnit.MINUTES)
        .build()
    private const val BASE_URL = "https://s3vior22.herokuapp.com/api/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        val apiService: MafqoudApiService = retrofit.create(
            MafqoudApiService::class.java)

        val userApi: UserApi = retrofit.create(
            UserApi::class.java)


}