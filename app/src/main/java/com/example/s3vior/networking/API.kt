package com.example.s3vior.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API{

        private const val BASE_URL = "http://192.168.1.4:8000/api/"
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: PersonApiService = retrofit.create(
            PersonApiService::class.java)
}