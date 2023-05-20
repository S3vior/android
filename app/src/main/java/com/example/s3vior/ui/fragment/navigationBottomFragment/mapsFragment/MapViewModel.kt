package com.example.s3vior.ui.fragment.navigationBottomFragment.mapsFragment

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.Location
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.GetAllMapsUseCase
import com.example.s3vior.domain.usecases.GetAllPersonsUseCase
import com.example.s3vior.domain.usecases.GetPersonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getAllPersonsUseCase: GetAllMapsUseCase,
  private val getPersonDetailsUseCase: GetPersonDetailsUseCase
    ) : ViewModel() {



    private val _personsStateFlow =
        MutableStateFlow<State<List<MafqoudModel>>>(State.Loading)
    val personsStateFlow: StateFlow<State<List<MafqoudModel>>> = _personsStateFlow

    val filter = MutableLiveData<String>()

    private val _personsDetailsStateFlow =
        MutableStateFlow<State<MafqoudModel>>(State.Success(
            MafqoudModel(
                age = "50",
                createdAt = "213",
                description = "null",
                gender = "null",
                id = 100,
                image = "null",
                location = Location(
                    latitude = 29.4574515,
                    longitude = 30.1319497,
                    address = "Maurio"
                ),
                name = "null",
                type = "null"
            )
        ))
    val personsDetailsStateFlow: StateFlow<State<MafqoudModel>> = _personsDetailsStateFlow


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

    init {
        getAllPersons()
    }


     fun getAllPersons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getAllPersonsUseCase.invoke().collectLatest {
                    _personsStateFlow.value = it
                }
            } catch (e: Exception) {
                _personsStateFlow.value = State.Error(e.message.toString())
            }

        }

    }


}

