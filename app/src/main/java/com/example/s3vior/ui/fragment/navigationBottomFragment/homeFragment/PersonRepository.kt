package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import com.example.s3vior.model.State
import com.example.s3vior.networking.API
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class PersonRepository {

    suspend fun getAllPersons() : Flow<State<List<Person>?>>{

        return wrapWithFlow(API.apiService::getAllPersons)
    }

    private fun <T> wrapWithFlow(function : suspend () -> Response< T> ):Flow<State<T?>>{
        return flow {
            emit(State.Loading)
            try {
                val result = function()
                if (result.isSuccessful){
                    delay(1000)
                    emit(State.Success(result.body()))
                }else{
                    emit(State.Error(result.message()))
                }
            }catch (e:Exception){
                emit(State.Error(e.message.toString()))
            }
        }
    }
}