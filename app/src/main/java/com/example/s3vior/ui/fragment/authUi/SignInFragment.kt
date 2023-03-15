package com.example.s3vior.ui.fragment.authUi

import android.content.Intent
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
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.cache.UserInfo.saveUserData
import com.example.s3vior.databinding.NewLoginFragmentBinding
import com.example.s3vior.domain.model.User
import com.example.s3vior.networking.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInFragment : Fragment(), TextWatcher {

    private lateinit var binding: NewLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.new_login_fragment, container, false)

        binding.loginButton.setOnClickListener { v ->
            navigationToMainFragment(v)
        }

        binding.tvSignUp.setOnClickListener {
            navigationToSignupFragment(it)
        }
        return binding.root
    }


    private fun initUserClick(v: View) {
        binding.apply {
            loginButton.setOnClickListener {
                val user_name = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                val valid = user_name.isEmpty() || password.isEmpty()

                if (valid) {
                    binding.emailEditText.error = "ادخل بريدك الالكتروني"
                    binding.passwordEditText.error = "اكتب كلمة السر"
                } else {
                    val user = User(null, user_name, "", password, "")
                    loginUser(user,v)
                }
            }
        }
    }

    private fun loginUser(user: User,v: View) {
        val retrofitBuilder = API.userApi.signIn(user)

        retrofitBuilder.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 200) {
                    Toast.makeText(requireContext(), "Not 200", Toast.LENGTH_SHORT).show()

                    return
                }
                val responseUser = response.body() as User
                saveUserData(requireContext(), responseUser)
                navigationToMainFragment(v)


            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), "خطأ فى التسجيل", Toast.LENGTH_SHORT).show()
            }
        })
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