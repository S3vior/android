package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.State
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {

    private val repository = PersonRepository()


    private val _personsStateFlow = MutableStateFlow<State<List<Person>?>>(State.Loading)
    val  personsStateFlow: StateFlow<State<List<Person>?>> = _personsStateFlow

    init {
        getAllPersons()
    }

    fun getAllPersons() {
        viewModelScope.launch{
            repository.getAllPersons().collect {
                _personsStateFlow.value = it
              }
        }
    }

}