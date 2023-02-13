package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.ValidateResult
import javax.inject.Inject

class ValidatePersonNameUseCase @Inject constructor(){
    operator fun invoke(name:String): ValidateResult {
        if (name.isBlank())
            return ValidateResult(error = "Please enter name")
        if (name.length<3)
            return ValidateResult(error = "Name is so short")
        return ValidateResult()
    }
}