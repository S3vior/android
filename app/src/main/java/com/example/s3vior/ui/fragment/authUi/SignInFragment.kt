package com.example.s3vior.ui.fragment.authUi

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.cache.UserInfo
import com.example.s3vior.cache.UserInfo.saveUserData
import com.example.s3vior.data.source.remote.endPoints.Fcm
import com.example.s3vior.data.source.remote.endPoints.UserApi
import com.example.s3vior.databinding.NewLoginFragmentBinding
import com.example.s3vior.domain.model.User
import com.example.s3vior.networking.API
import com.example.s3vior.utils.Constants
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
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
class SignInFragment : Fragment(), TextWatcher {

    private lateinit var binding: NewLoginFragmentBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.new_login_fragment, container, false)




        binding.loginButton.setOnClickListener { v ->
            initUserClick(v)

        }

        binding.tvSignUp.setOnClickListener {
            navigationToSignupFragment(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (UserInfo.isUserLoggedIn(requireContext())){
            navigationToMainFragment(requireView())
        }
        super.onViewCreated(view, savedInstanceState)

    }


    private fun initUserClick(v: View) {

        val user_name = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val valid = user_name.isBlank() || password.isBlank()

        if (valid) {
            binding.emailEditText.error = "ادخل بريدك الالكتروني"
            binding.passwordEditText.error = "اكتب كلمة السر"
        } else {
            val user = UserApi.SignInFields(user_name, password)
            loginUser(user, v)

        }
    }

    private fun loginUser(user: UserApi.SignInFields, v: View) {
        lifecycleScope.launch {
            try {


                val token = FirebaseMessaging.getInstance().token.await()

                val signInResult = signInViewModel.SignInUseCase.invoke(user).also {

                    signInViewModel.FCMUseCase.invoke(
                        "Bearer " + it.token.toString(),
                        Fcm(signInViewModel.fcmToken.value)
                     )
                    Log.d("fmcToken",signInViewModel.fcmToken.value)
                }



                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), signInResult.result, Toast.LENGTH_LONG).show()
                    if (signInResult.result == "login successfully") {
                        navigationToMainFragment(v)

                    }
                    Log.d("userSignIn", signInResult.result)
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "something error", Toast.LENGTH_LONG).show()
            }
        }


    }

//    override fun onBackPressed() {
//        finish()
//    }

    private fun navigationToMainFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_homeFragment)
    }


    private fun navigationToSignupFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_singUpFragment)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        //edited
        binding.loginButton.isEnabled =
            binding.passwordEditText.text.isEmpty() && binding.emailEditText.text.isEmpty()
    }

    override fun afterTextChanged(s: Editable?) {
    }

}