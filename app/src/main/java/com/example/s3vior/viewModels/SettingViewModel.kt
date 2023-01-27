package com.example.s3vior.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.SettingData
import com.example.s3vior.R
import com.example.s3vior.SettingDataRepository
import com.example.s3vior.model.State

class SettingViewModel : ViewModel() {

    private val repository = SettingDataRepository()

    private val _itemSettingData = MutableLiveData<State<List<SettingData?>>>()
    val itemSettingData: LiveData<State<List<SettingData?>>>
        get() = _itemSettingData

    private fun getData() {
        val s= State.Success<List<SettingData>>(repository.getSettingData())
        _itemSettingData.postValue(s)
        Log.e("jkdlsfjlks",_itemSettingData.value.toString())
    }

    init {
        getData()
    }
}