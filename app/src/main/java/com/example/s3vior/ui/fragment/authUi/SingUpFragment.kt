package com.example.s3vior.ui.fragment.authUi

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
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.cache.UserInfo.saveUserData
import com.example.s3vior.databinding.FragmentSingUpBinding

import com.example.s3vior.databinding.NewSignupFragmentBinding
import com.example.s3vior.domain.model.User
import com.example.s3vior.networking.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingUpFragment : Fragment() {
    private lateinit var binding: NewSignupFragmentBinding

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
        val valid = name.isEmpty() || mobile.isEmpty() || password.isEmpty()

        if (valid) {
            binding.edSignUpFirstName.error = "اكتب اسمك"
            binding.phone.error = "ادخل رقم تليفونك"
            binding.password.error = "اختر كلمة سر لا تقل عن 6 أحرف"
            return
        } else {
            val user = User(null, name, mobile, password)
            createUser(user, v)
        }

    }

    private fun createUser(user: User,v: View) {
        val retrofitBuilder =API.userApi.signUp(user)

        retrofitBuilder.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() != 201) {
                    Toast.makeText(requireContext(), "Not 200", Toast.LENGTH_SHORT).show()
                    return
                }
                val responseUser = response.body() as User
                Log.d("response",responseUser.toString())
                navigationToMainFragment(v)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), "خطأ فى التسجيل", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun navigationToMainFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_singUpFragment_to_homeFragment)
    }
}