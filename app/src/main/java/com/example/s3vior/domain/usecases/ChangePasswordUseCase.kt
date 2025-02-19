package com.example.s3vior.domain.usecases

import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        token: String,
        request: PasswordChangeRequest
    ): String {

        return try {
            userRepository.changeUserPassword(
                token = token,
                request
            )
        }catch (e:Exception){
            "something wrong"
        }
    }


}
