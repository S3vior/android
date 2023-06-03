package com.example.s3vior.ui.fragment.authUi

import androidx.lifecycle.ViewModel
import com.example.s3vior.domain.usecases.FCMUseCase
import com.example.s3vior.domain.usecases.SignInUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val FCMUseCase: FCMUseCase,
    val SignInUseCase: SignInUserCase
)  :ViewModel() {
}