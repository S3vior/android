package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetAllPersonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getAllPersonsUseCase: GetAllPersonsUseCase
) : ViewModel() {

    private val repository = PersonRepository()


    private val _personsStateFlow =
        MutableStateFlow<State<List<MafqoudModel>>>(State.Loading)
    val personsStateFlow: StateFlow<State<List<MafqoudModel>>> = _personsStateFlow

    val filter = MutableLiveData<String>()

    init {
        getAllPersons()
    }

//    fun <T> searchFunctionality(searchWard: String, items: List<MafqoudResponseModel>?) {
//        val filteredList: ArrayList<MafqoudResponseModel> = ArrayList()
//
//        for (item in items!!) {
//            if (item.name.toLowerCase().contains(searchWard.toLowerCase())) filteredList.add(item)
//        }
//        if (filteredList.isNotEmpty()) _personsStateFlow.value = State.Success(filteredList)
//    }

    fun getAllPersons() {
        viewModelScope.launch {
//            repository.getAllPersons().collect {
//                _personsStateFlow.value = it

//                //  Log.d("ajbzoa",it.toString())
//            }
            try {
                getAllPersonsUseCase.invoke().collect {
                    _personsStateFlow.value = State.Success(it)
                    Log.d("theFuckingData", it.toString())
                    Log.d("theFuckingData", State.Success(it).toString())
                    Log.i("theFuckingData", State.Success(it).data.toString())
                    Log.i("theFuckingData", State.Success(it).toData().toString())
                    Log.i("theFuckingData", _personsStateFlow.value.toString())
                    // State.Success(it).toData()!!
                }
            } catch (e: Exception) {
                Log.d("erroo", e.message.toString())
            }

        }

        Log.i("theFuckingData", _personsStateFlow.value.toData().toString())
    }


    fun searchFoePerson(name: String) {
        viewModelScope.launch {
            repository.searchForPerson(name).collect {
                //   _personsStateFlow.value = it.toData().map {  }
                Log.d("ajbzoa", it.toString())
            }
        }
    }

}