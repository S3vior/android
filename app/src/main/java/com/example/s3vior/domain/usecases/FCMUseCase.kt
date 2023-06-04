package com.example.s3vior.domain.usecases

import com.example.s3vior.data.source.remote.endPoints.Fcm
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class FCMUseCase@Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(token:String,fcmToken:Fcm):String{
        return try {
            userRepository.fcmToken(token, fcmToken)
        } catch (e:Exception){
            "something error"
        }
    }
}