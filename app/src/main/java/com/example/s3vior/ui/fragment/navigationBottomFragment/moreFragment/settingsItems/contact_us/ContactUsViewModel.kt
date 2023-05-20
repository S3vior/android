package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.contact_us

import androidx.lifecycle.ViewModel
import com.example.s3vior.domain.usecases.ContactUsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ContactUsViewModel @Inject constructor(
    var contactUsUseCase: ContactUsUseCase
) : ViewModel() {
}