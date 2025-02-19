package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetMatchedPersonsUseCase
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchedPersonsViewModel @Inject constructor(
    val matchedPersonUseCase: GetMatchedPersonsUseCase
) : ViewModel() {

    private val _matchedPersonsStateFlow =
        MutableStateFlow<State<List<MatchedPersonsResponseModelItem>>>(State.Loading)
    val matchedPersonsStateFlow: StateFlow<State<List<MatchedPersonsResponseModelItem>>> =
        _matchedPersonsStateFlow

    init {
        getMatchedPersons()
    }

      fun getMatchedPersons() {
        viewModelScope.launch(Dispatchers.IO)  {
            try {
                matchedPersonUseCase.invoke().collect{
                    _matchedPersonsStateFlow.value = it
                }
            }catch (e:Exception){
                _matchedPersonsStateFlow.value = State.Error(e.message.toString())
            }
        }

    }

}