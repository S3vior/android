package com.example.s3vior.ui.fragment.authUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.domain.usecases.FCMUseCase
import com.example.s3vior.domain.usecases.SignInUserCase
import com.example.s3vior.domain.usecases.SignUpUserCase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val FCMUseCase: FCMUseCase,
    val SignInUseCase: SignInUserCase,
    val signUpUserCase: SignUpUserCase
)  :ViewModel() {

    private val _fcmToken =
        MutableStateFlow("")
    val  fcmToken: StateFlow<String> = _fcmToken

    init {
        viewModelScope.launch {
            _fcmToken.value = FirebaseMessaging.getInstance().token.await()
        }
    }
}