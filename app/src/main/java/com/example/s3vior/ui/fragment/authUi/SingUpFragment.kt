package com.example.s3vior.ui.fragment.authUi

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.cache.UserInfo.saveUserData
import com.example.s3vior.data.source.remote.endPoints.Fcm
import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.databinding.FragmentSingUpBinding

import com.example.s3vior.databinding.NewSignupFragmentBinding
import com.example.s3vior.domain.model.User
import com.example.s3vior.networking.API
import com.example.s3vior.utils.Constants
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@AndroidEntryPoint
class SingUpFragment : Fragment() {
    private lateinit var binding: NewSignupFragmentBinding

    private val viewModel : SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.new_signup_fragment, container, false)
        binding.btnSignUp.setOnClickListener {
            initUser(it)
        }
        return binding.root
    }


    private fun initUser(v: View) {

        val name = binding.edSignUpFirstName.text.toString()
        val mobile = binding.phone.text.toString()
        val password = binding.password.text.toString()
        val valid = name.isBlank() || mobile.isBlank() || password.isBlank()

        if (valid) {
            binding.edSignUpFirstName.error = "اكتب اسمك"
            binding.phone.error = "ادخل رقم تليفونك"
            binding.password.error = "اختر كلمة سر لا تقل عن 6 أحرف"
            return
        } else {
            val user = UserApi.SignUpFields(  name, password,mobile)
            createUser(user, v)
        }

    }

    private fun createUser(user: UserApi.SignUpFields,v: View) {

        lifecycleScope.launch {
            val token = FirebaseMessaging.getInstance().token.await()

            withContext(Dispatchers.Main){
                Toast.makeText(requireContext(),token,Toast.LENGTH_LONG).show()
            }
           val signUpResult =  viewModel.signUpUserCase.invoke(user)
               .also {
               viewModel.FCMUseCase.invoke(
                   "Bearer "+it.token.toString(),
                   Fcm(viewModel.fcmToken.value)
               )
           }

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), signUpResult.result, Toast.LENGTH_LONG).show()
                if (signUpResult.result == "login successfully") {
                    navigationToMainFragment(v)

                }
                Log.d("userSignIn", signUpResult.result)
            }
        }


    }


    private fun navigationToMainFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_singUpFragment_to_homeFragment)
    }
}