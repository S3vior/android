package com.example.s3vior.data.repositories

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.s3vior.cache.UserInfo
import com.example.s3vior.data.source.remote.dataSource.UserRemoteDataSource
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.data.source.remote.endPoints.Fcm
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource ,
                                             private val appContext: Context) :
    UserRepository {
    override suspend fun changeUserPassword(
        token: String,
        request: PasswordChangeRequest
    ): String {
        val changePasswordResult = userRemoteDataSource.changeUserPassword(
            token,
            request
        )

        return if (changePasswordResult.isSuccessful && changePasswordResult.body() != null && changePasswordResult.code() == 200) {

            Log.d("fucking", changePasswordResult.body()!!.source().toString())
            "Password updated successfully."
        } else {
            "Failed to updated Password"
        }

    }

    override suspend fun signIn(signInFields: UserApi.SignInFields): SignInResult {
      val signInResult = userRemoteDataSource.signIn(signInFields)
        return if (signInResult.isSuccessful ){
            Log.d("userToken",signInResult.body()!!.token)
            UserInfo.saveUserData( appContext, signInResult.body()!!.token )

            SignInResult("login successfully" ,signInResult.body()!!.token )

        }else{
            SignInResult( "username or password is wrong" ,"" )

        }
    }

    data class SignInResult(val result : String,val token: String?)
    override suspend fun contactUs(token: String, contactUs: ContactUs): String {


        val contactUsResult = userRemoteDataSource.contactUs(
            token, contactUs
        )
        return if (contactUsResult.isSuccessful && contactUsResult.body() != null && contactUsResult.code() == 200) {
            "problem send successfully"
        } else {
            "something error"
        }
    }

    override suspend fun fcmToken(token: String, fcmToken: Fcm) :String{
         val fcmResult = userRemoteDataSource.fcmToken(token, fcmToken)
        return if (fcmResult.isSuccessful && fcmResult.body()!= null&&fcmResult.code() == 200){
            "fcm send successfully"
        }else{
            "fcm not send successfully"
        }

    }

}
