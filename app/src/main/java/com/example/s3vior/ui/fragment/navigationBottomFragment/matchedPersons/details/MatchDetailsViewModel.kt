package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.MatchDetailsUseCase
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.model.MatchedPersonsResponseModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val matchDetailsUseCase: MatchDetailsUseCase
) : ViewModel() {

    private val _personsDetailsStateFlow =
        MutableStateFlow<State<MatchedPersonsResponseModelItem>>(State.Loading)
    val personsStateFlow: StateFlow<State<MatchedPersonsResponseModelItem>> = _personsDetailsStateFlow
    fun getMatchedDetails(matchId: Int) {
        viewModelScope.launch {
            try {
                _personsDetailsStateFlow.value =
                    State.Success(matchDetailsUseCase.invoke(matchId)).toData()!!

            } catch (e: Exception) {
                _personsDetailsStateFlow.value = State.Error(e.message.toString())
            }
        }
    }

}