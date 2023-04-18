package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.model.User
import com.example.s3vior.domain.model.UserInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    private val repository = PersonRepository()


    private val _personsStateFlow = MutableStateFlow<State<List<Person>?>>(State.Loading)
    val personsStateFlow: StateFlow<State<List<Person>?>> = _personsStateFlow

    val filter = MutableLiveData<String>()

    init {
        getAllPersons()
    }

    fun <T> searchFunctionality(searchWard: String, items: List<Person>?) {
        val filteredList: ArrayList<Person> = ArrayList()

        for (item in items!!) {
            if (item.name.toLowerCase().contains(searchWard.toLowerCase())) filteredList.add(item)
        }
        if (filteredList.isNotEmpty()) _personsStateFlow.value = State.Success(filteredList)
    }

    fun getAllPersons() {
        viewModelScope.launch {
            repository.getAllPersons().collect {
                _personsStateFlow.value = it
            }
        }
    }

}