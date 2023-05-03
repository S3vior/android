package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import com.example.s3vior.data.source.remote.responseModels.MafqoudResponseModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.networking.API
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersonRepository   {

//    suspend fun getAllPersons() : Flow<State<List<MafqoudResponseModel>?>>{
//
//          return wrapWithFlow(function = mafqoudRepository::getAllPersons)
//
//    }


//    private fun <T> wrapWithFlow( function : suspend () -> Response<T> ):Flow<State<T?>>{
//        return flow {
//            emit(State.Loading)
//            delay(500)
//            try {
//                val result = function()
//                if (result.isSuccessful){
//                    delay(1000)
//                    emit(State.Success(result.body()))
//                }else{
//                    emit(State.Error(result.message()))
//                }
//            }catch (e:Exception){
//                emit(State.Error(e.message.toString()))
//            }
//        }
//    }
    suspend fun getAllPersons() : Flow<State<List<MafqoudResponseModel>?>>{
        val result =  API.apiService.getAllPersons()
        return if (result.isSuccessful){
            flow {
                emit(State.Success(result.body()))
            }
        }else {
            flow {
                emit(State.Error(result.message()))
            }
        }
    }
    suspend fun searchForPerson(name:String): Flow<State<List<MafqoudResponseModel>?>>{
        val result =  API.apiService.searchForPerson(name)
        return if (result.isSuccessful){
            flow {
                emit(State.Success(result.body()))
            }
        }else {
            flow {
                emit(State.Error(result.message()))
            }
        }
     }
}