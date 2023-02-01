package com.example.s3vior.ui.fragment.authUi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.emailEditText.addTextChangedListener(this)
        binding.passwordEditText.addTextChangedListener(this)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener { v ->

                navigationToMainFragment(v)

        }

        binding.tvSignUp.setOnClickListener { navigationToSignupFragment(it) }
        return binding.root
    }

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