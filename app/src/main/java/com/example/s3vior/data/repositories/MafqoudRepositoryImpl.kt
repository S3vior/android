package com.example.s3vior.data.repositories

import com.example.s3vior.data.source.remote.dataSource.MafqoudRemoteDataSource
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.repositories.MafqoudRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject


class MafqoudRepositoryImpl @Inject constructor(
    private val mafqoudRemoteDataSource: MafqoudRemoteDataSource
) : MafqoudRepository {
    override suspend fun getAllPersons(): List<MafqoudModel> {
        val getPersonsResult = mafqoudRemoteDataSource.getAllPersons()
//        return getPersonsResult.body()!!.map {
//            it.toMafqoudModel()
//        }
        return if (getPersonsResult.isSuccessful && getPersonsResult.body() != null && getPersonsResult.code() == 200) {

            getPersonsResult.body()!!.map {
                it.toMafqoudModel()
            }
        } else {
            getPersonsResult.errorBody()!!.string()
            throw Exception(
                JSONObject(
                    getPersonsResult.errorBody()!!.string()
                ).getString("message")
            )
        }
    }

    override suspend fun uploadPerson(
        token: String,
        name: String,
        age: Int,
        gender: String,
        type: String,
        description: String,
        imageAsByte: ByteArray, extension: String
    ): String {
        val uploadPersonResult =
            mafqoudRemoteDataSource.uploadMafqoud(
                token,
                name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                age.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                gender.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                type.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                description.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                MultipartBody.Part.createFormData(
                    "image",
                    "image.$extension",
                    imageAsByte.toRequestBody("*/*".toMediaType())
                )
            )
        return if (uploadPersonResult.isSuccessful && uploadPersonResult.body()?.data != null && uploadPersonResult.code() == 200) {
//            Log.d("POSTResponse", "if" + uploadPersonResult.body()?.data.toString())
//            Log.d("POSTResponse", "if" + uploadPersonResult.body().toString())
            uploadPersonResult.body()?.data.toString()
        } else {
//            Log.d("POSTResponse", uploadPersonResult.body()?.data.toString())
//            Log.d("POSTResponse", uploadPersonResult.body().toString())

            uploadPersonResult.body()!!.message
//            val errorBody = JSONObject(uploadPersonResult.errorBody()!!.string())
//            throw Exception(errorBody.getString("message"))
        }


    }

    override suspend fun searchPerson(searchWord: String): List<MafqoudModel> {
        val getSearchResult = mafqoudRemoteDataSource.searchForMafqoud(searchWord)
        return if (getSearchResult.isSuccessful && getSearchResult.body() != null && getSearchResult.code() == 200) {
            getSearchResult.body()!!.map { mafqoudSearch ->
                mafqoudSearch.toMafqoudModel()
            }
        } else {
            emptyList()
        }


    }

}