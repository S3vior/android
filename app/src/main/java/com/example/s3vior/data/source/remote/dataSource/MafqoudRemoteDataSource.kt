package com.example.s3vior.data.source.remote.dataSource

import com.example.s3vior.data.source.remote.endPoints.MafqoudApiService
import com.example.s3vior.data.source.remote.responseModels.MafqoudResponseModel
import com.example.s3vior.data.source.remote.responseModels.ScrapedPersonsResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.security.DigestOutputStream
import javax.inject.Inject

class MafqoudRemoteDataSource @Inject constructor(private var mafqoudApiService: MafqoudApiService) {

    suspend fun getAllPersons(): Response<List<MafqoudResponseModel>> =
        withContext(Dispatchers.IO) {
            mafqoudApiService.getAllPersons()
        }

    suspend fun getFoundedPersons(): Response<List<MafqoudResponseModel>> =
        withContext(Dispatchers.IO) {
            mafqoudApiService.getFoundedPersons()
        }

    suspend fun getMissedPersons(): Response<List<MafqoudResponseModel>> =
        withContext(Dispatchers.IO) {
            mafqoudApiService.getMissedPersons()
        }
    suspend fun getScrapedPersons(): Response<List<ScrapedPersonsResponseItem>> =
        withContext(Dispatchers.IO) {
            mafqoudApiService.getScrapedPersons()
        }

    suspend fun getAllMaps() = withContext(Dispatchers.IO){
        mafqoudApiService.getAllPersonsMap()
    }


    suspend fun uploadMafqoud(
        token: String,
        name: RequestBody,
        age: RequestBody,
        gender: RequestBody,
        type: RequestBody,
        description: RequestBody,
        latitude: RequestBody,
        longitude: RequestBody,
        imageMultiPart: MultipartBody.Part
    ) = withContext(Dispatchers.IO) {
        mafqoudApiService.upLoadPerson(
           token =  token,
            name = name,
            age = age,
            gender = gender,
            type = type,
            description = description,
            latitude = latitude,
            longitude = longitude,
            image = imageMultiPart
        )
    }

    suspend fun searchForMafqoud(keyWord: String) = withContext(Dispatchers.IO) {
        mafqoudApiService.searchForPerson(keyWord)
    }
    suspend fun getMatchedPersons() = withContext(Dispatchers.IO){
        mafqoudApiService.getMatchedPersons()
    }


    suspend fun getPersonDetails(id:Int) = withContext(Dispatchers.IO){
        mafqoudApiService.getPersonDetails(id)
    }
}