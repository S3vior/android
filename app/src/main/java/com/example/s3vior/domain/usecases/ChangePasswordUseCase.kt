package com.example.s3vior.domain.usecases

import com.example.s3vior.domain.model.ValidateResult
import com.example.s3vior.domain.repositories.UserRepository
import com.example.s3vior.utils.InvalidInputTextException
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        token: String,
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ): String {

        return userRepository.changeUserPassword(token, oldPassword, newPassword, confirmPassword)
    }


}
