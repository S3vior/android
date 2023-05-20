package com.example.s3vior.data.source.remote.endPoints

import com.example.s3vior.data.source.remote.responseModels.MafqoudResponseModel
import com.example.s3vior.networking.api.BaseApiResponse
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MafqoudApiService {
    @GET("persons")
    suspend fun getAllPersons(): Response<List<MafqoudResponseModel>>

    @GET("persons")
    suspend fun getAllPersonsMap(): Response<List<MafqoudResponseModel>>


    @GET("persons/search")
    suspend fun searchForPerson(
        @Query("name") name: String
    ): Response<List<MafqoudResponseModel>>


    @POST("persons")
    @Multipart
    suspend fun upLoadPerson(
        @Header("token") token: String,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("type") type: RequestBody,
        @Part("description") description: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<ResponseBody>


    @GET("matches")
    suspend fun getMatchedPersons(): Response<List<MatchedPersonsResponseModelItem>>

    @GET("persons/{id}")
    suspend fun getPersonDetails(
        @Path("id") id: Int
    ):Response<MafqoudResponseModel>
}


