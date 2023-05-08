package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.usecases.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
      val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

}