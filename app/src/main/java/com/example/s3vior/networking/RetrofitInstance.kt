package com.example.s3vior.networking
import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance(){
    private val BASE_URL = "http://192.168.1.11:8000/api/"

    val mOkHttpClient = OkHttpClient
        .Builder()
        .callTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }
        val personApi: PersonApiService by lazy {
            retrofit.create(PersonApiService::class.java)
        }

    }
