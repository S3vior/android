package com.example.s3vior.data.source.remote.endPoints

import com.example.s3vior.data.source.remote.responseModels.MafqoudResponseModel
import com.example.s3vior.networking.api.BaseApiResponse
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MafqoudApiService {
    @GET("persons")
    suspend fun getAllPersons(): Response<BaseApiResponse<List<MafqoudResponseModel>>>


    @GET("persons/search")
    suspend fun searchForPerson(
        @Query("name") name: String?
    ): Response<BaseApiResponse<List<MafqoudResponseModel>>>


    @POST("persons")
    @Multipart
    suspend fun upLoadPerson(
        @Header("token")   token: String,
        @Part("name")   name: RequestBody,
        @Part("age")   age: RequestBody,
        @Part("gender")   gender: RequestBody,
        @Part("type")   type: RequestBody,
        @Part("description")   description: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<BaseApiResponse<String>>

}


