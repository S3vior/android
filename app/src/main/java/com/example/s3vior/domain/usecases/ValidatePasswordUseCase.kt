package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.ValidateResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): ValidateResult {
        if (password.isBlank())
            return ValidateResult(error = "Please enter password")
        if (!password.matches(VALID_PASSWORD_REGEX))
            return ValidateResult(error = "Please enter valid password")
        return ValidateResult()
    }
}

val VALID_PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^A-Za-z0-9]).{8,}\$".toRegex()