package com.example.s3vior.ui.fragment.scrapedPersons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.data.source.remote.responseModels.ScrapedPersonsResponseItem
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetScrapedPersonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScrapedViewModel @Inject constructor(
    private val getScrapedPersonsUseCase: GetScrapedPersonsUseCase,
) : ViewModel() {
    private val _personsStateFlow =
        MutableStateFlow<State<List<ScrapedPersonsResponseItem>>>(State.Loading)
    val personsStateFlow: StateFlow<State<List<ScrapedPersonsResponseItem>>> = _personsStateFlow



    init {
        getScrapedPersons()
    }

    fun getScrapedPersons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getScrapedPersonsUseCase.invoke().collect {
                    _personsStateFlow.value = it
                }
            } catch (e: Exception) {
                _personsStateFlow.value = State.Error(e.message.toString())
            }
        }
    }
}