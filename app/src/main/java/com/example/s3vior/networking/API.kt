package com.example.s3vior.networking

import com.example.s3vior.networking.api.PersonApi
import com.example.s3vior.networking.api.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object API {
    val mOkHttpClient = OkHttpClient
        .Builder()
        .callTimeout(10, TimeUnit.MINUTES)
        .connectTimeout(10, TimeUnit.MINUTES)
        .build()
    private const val BASE_URL = "http://192.168.1.2:8000/api/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        val apiService: PersonApi = retrofit.create(
            PersonApi::class.java)
        val userApi: UserApi = retrofit.create(
            UserApi::class.java)


}