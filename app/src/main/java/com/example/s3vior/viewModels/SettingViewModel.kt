package com.example.s3vior.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.SettingData
import com.example.s3vior.R
import com.example.s3vior.SettingDataRepository
import com.example.s3vior.model.State
import com.example.s3vior.ui.recyclerView.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    private val repository = SettingDataRepository()

    private val _itemSettingData = MutableLiveData<State<List<SettingData>>>()
    val itemSettingData: LiveData<State<List<SettingData>>>
         = _itemSettingData
    init {
        getData()
    }
    private fun getData() {
        viewModelScope.launch {
            repository.getData().collect{
                _itemSettingData.postValue(it)
            }
        }
    }
}