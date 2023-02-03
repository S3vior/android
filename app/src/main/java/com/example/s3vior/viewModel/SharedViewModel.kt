package com.example.s3vior.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s3vior.viewModel.model.FirstDetails
import com.example.s3vior.viewModel.model.SecondDetails

class SharedViewModel : ViewModel() {

    private val _firstDetails = MutableLiveData(FirstDetails("no name", "no description"))
    val firstDetails: LiveData<FirstDetails> = this._firstDetails

    private val _secondDetails = MutableLiveData(SecondDetails("","","","",""))
    val secondDetails :LiveData<SecondDetails> = _secondDetails


    fun getDataFromNameFragment(newDetails: FirstDetails) {
        _firstDetails.value?.apply {
            name = newDetails.name
            description = newDetails.description
        }
    }

    fun getDataFromAgeGenderFragment(newDetails: SecondDetails){
        _secondDetails.value?.apply {
            year = newDetails.year
            month = newDetails.month
            day = newDetails.day
            age = newDetails.age
            gender = newDetails.gender

        }
    }


}