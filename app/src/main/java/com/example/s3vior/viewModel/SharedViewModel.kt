package com.example.s3vior.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s3vior.domain.usecases.UploadPersonUseCase
import com.example.s3vior.domain.usecases.ValidatePersonNameUseCase
import com.example.s3vior.viewModel.model.FirstDetails
import com.example.s3vior.viewModel.model.SecondDetails
import com.example.s3vior.viewModel.model.ThirdDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    var uploadPersonUseCase: UploadPersonUseCase
) : ViewModel() {

    private val _firstDetails = MutableLiveData(FirstDetails("n", "no description"))
    val firstDetails: LiveData<FirstDetails> = this._firstDetails

    private val _secondDetails = MutableLiveData(SecondDetails("", "", "", "", ""))
    val secondDetails: LiveData<SecondDetails> = _secondDetails

    private val _thirdDetails = MutableLiveData(ThirdDetails(null))
    val thirdDetails: LiveData<ThirdDetails> = _thirdDetails

    private val useCase = ValidatePersonNameUseCase()

    private val _mafqoudStatus = MutableLiveData("")
    val mafqoudStatus: LiveData<String> = _mafqoudStatus




    fun getMafqoudStatus(status: String) {
        _mafqoudStatus.value = status
    }

    fun getDataFromGalleryFragment(newDetails: ThirdDetails) {
        _thirdDetails.value?.imageUri = newDetails.imageUri
    }

    fun getDataFromNameFragment(newDetails: FirstDetails) {
        val result = useCase(newDetails.name)

        if (result.error != null) {
            //

        } else {
            _firstDetails.value?.apply {
                name = newDetails.name
                description = newDetails.description
            }
        }

    }

    fun getDataFromAgeGenderFragment(newDetails: SecondDetails) {
        _secondDetails.value?.apply {
            year = newDetails.year
            month = newDetails.month
            day = newDetails.day
            age = newDetails.age
            gender = newDetails.gender

        }
    }


}