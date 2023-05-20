package com.example.s3vior.ui.fragment.addPersonDetails.personDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetPersonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllPersonInfoViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModel() {


    private val _personsDetailsStateFlow =
        MutableStateFlow<State<MafqoudModel>>(State.Loading)
    val personsStateFlow: StateFlow<State<MafqoudModel>> = _personsDetailsStateFlow


    suspend fun getPersonDetails(id: Int) {
        viewModelScope.launch {
            try {
                _personsDetailsStateFlow.value =
                    State.Success(getPersonDetailsUseCase.invoke(id)).toData()!!
            } catch (e: Exception) {
                _personsDetailsStateFlow.value = State.Error(e.message.toString())
            }
        }
    }
}