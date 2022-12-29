package com.example.s3vior.ui.fragment.authUi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentSignInBinding


class SignInFragment : Fragment(), TextWatcher  {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.emailEditText.addTextChangedListener(this)
        binding.passwordEditText.addTextChangedListener(this)


        binding.loginButton.setOnClickListener { navigationToMainFragment(it) }
        binding.tvSignUp.setOnClickListener {  navigationToSignupFragment(it)}
        return binding.root
    }


    private fun navigationToMainFragment(v:View){
        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_homeFragment)
    }


    private fun navigationToSignupFragment(v:View){
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