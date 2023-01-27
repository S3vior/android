package com.example.s3vior.ui.fragment.navigationBottomFragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.model.State
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.PersonRepository
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    private val repository = PersonRepository()


    private val personsLiveData = MutableLiveData<State<List<Person>?>>()
    val _personsLiveData: LiveData<State<List<Person>?>> = personsLiveData


    init {
        getAllPersons()
    }


    fun getAllPersons() {
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllPersons().collect {
                personsLiveData.postValue(it)
            }
        }
    }

}