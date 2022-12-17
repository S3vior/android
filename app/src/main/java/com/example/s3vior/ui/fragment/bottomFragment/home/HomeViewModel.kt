package com.example.s3vior.ui.fragment.bottomFragment.home

import android.content.Context
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s3vior.networking.Repository.PersonRepository
import com.example.s3vior.networking.RetrofitInstance
import com.example.s3vior.ui.recyclerView.Person
import io.reactivex.rxjava3.internal.util.HalfSerializer.onError
import kotlinx.coroutines.*
import retrofit2.Response
import javax.security.auth.callback.Callback

class PersonViewModel : ViewModel() {

    val personsLiveData = MutableLiveData<List<Person>>()
    lateinit var personList: List<Person>

    fun getPersons() {
        val retrofitBuilder = RetrofitInstance().personApi.getAllPersons()

        retrofitBuilder.enqueue(object : retrofit2.Callback<List<Person>> {
            override fun onResponse(
                call: retrofit2.Call<List<Person>>,
                response: Response<List<Person>>
            ) {
                if (response.isSuccessful) {
                    personsLiveData.value = response.body()
                    personList = personsLiveData.value!!
                    Log.d("data", "initCategoryObserve:$personList ")


                }
            }

            override fun onFailure(call: retrofit2.Call<List<Person>>, t: Throwable) {
                Log.d("TAG", "onFailure:${t.message} ")
            }
        })
    }
}