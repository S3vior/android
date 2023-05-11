package com.example.s3vior.data.repositories

import android.util.Log
import com.example.s3vior.data.source.remote.dataSource.MafqoudRemoteDataSource
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.repositories.MafqoudRepository
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
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

    override suspend fun getMatchedPersons(): List<MatchedPersonsResponseModelItem> {
         val getMatchedPersonsResult = mafqoudRemoteDataSource.getMatchedPersons()
        return if (getMatchedPersonsResult.isSuccessful&& getMatchedPersonsResult.body() != null && getMatchedPersonsResult.code() == 200){
            getMatchedPersonsResult.body()!!
        }else{
            getMatchedPersonsResult.errorBody()!!.string()
            throw Exception(
                JSONObject(
                    getMatchedPersonsResult.errorBody()!!.string()
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
        latitude:String,
        longitude:String,
        imageAsByte: ByteArray, extension: String
    ): String {
        val uploadPersonResult =
            mafqoudRemoteDataSource.uploadMafqoud(
                token,
                name .toRequestBody("text/plain".toMediaTypeOrNull())  ,
                age.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                gender .toRequestBody("text/plain".toMediaTypeOrNull()),
                type .toRequestBody("text/plain".toMediaTypeOrNull()),
                description .toRequestBody("text/plain".toMediaTypeOrNull()),
                latitude.toRequestBody("text/plain".toMediaTypeOrNull()),
                longitude.toRequestBody("text/plain".toMediaTypeOrNull()),
                MultipartBody.Part.createFormData(
                    "image",
                    "image.$extension",
                    imageAsByte.toRequestBody("*/*".toMediaType())
                )
            )

        return if (uploadPersonResult.isSuccessful ) {
             Log.d("POSTResponse", "if" + uploadPersonResult.body().toString())
             Log.d("POSTResponse", "if" + uploadPersonResult.body().toString())
            "Person uploaded"
        } else {
            "Person not uploaded"
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