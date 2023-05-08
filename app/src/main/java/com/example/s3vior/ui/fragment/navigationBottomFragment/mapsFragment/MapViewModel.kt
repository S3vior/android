package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetAllPersonsUseCase
import com.example.s3vior.domain.usecases.SearchPersonUseCase
import com.example.s3vior.room.DatabaseRepo
import com.example.s3vior.room.PersonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getAllPersonsUseCase: GetAllPersonsUseCase,

    ) : ViewModel() {


    private val _personsStateFlow =
        MutableStateFlow<State<List<MafqoudModel>>>(State.Loading)
    val personsStateFlow: StateFlow<State<List<MafqoudModel>>> = _personsStateFlow

    val filter = MutableLiveData<String>()

    init {
        getAllPersons()
    }


    @SuppressLint("SuspiciousIndentation")
    fun getAllPersons() {
        viewModelScope.launch {
            try {
                getAllPersonsUseCase.invoke().collect {
                    _personsStateFlow.value = it
                }
            } catch (e: Exception) {
                _personsStateFlow.value = State.Error(e.message.toString())
            }

        }

    }


}

