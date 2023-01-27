package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.model.State
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