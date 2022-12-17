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

    val categoriesLiveData = MutableLiveData<List<Person>>()
    lateinit var categoryList: List<Person>

    fun loadCategories() {
        val retrofitBuilder = RetrofitInstance().personApi.getAllPersons()

        retrofitBuilder.enqueue(object : retrofit2.Callback<List<Person>> {
            override fun onResponse(
                call: retrofit2.Call<List<Person>>,
                response: Response<List<Person>>
            ) {
                if (response.isSuccessful) {
                    categoriesLiveData.value = response.body()
                    categoryList = categoriesLiveData.value!!
                    Log.d("data", "initCategoryObserve:$categoryList ")


                }
            }

            override fun onFailure(call: retrofit2.Call<List<Person>>, t: Throwable) {
                Log.d("TAG", "onFailure:${t.message} ")
            }
        })
    }
}