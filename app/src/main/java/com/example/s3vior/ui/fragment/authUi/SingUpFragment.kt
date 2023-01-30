package com.example.s3vior.ui.fragment.authUi

import android.os.Build
import android.os.Bundle
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
import com.example.s3vior.databinding.FragmentSingUpBinding
import com.google.firebase.auth.FirebaseAuth

class SingUpFragment : Fragment() {
    private lateinit var binding: FragmentSingUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sing_up, container, false)

        binding.btnSignUp.setOnClickListener { v ->
            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(
                binding.emailEditTextSignUp.text.toString(),
                binding.passwordEditTextSignup.text.toString()
            ).addOnSuccessListener {
                navigationToMainFragment(v)
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return binding.root
    }

    private fun navigationToMainFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_singUpFragment_to_homeFragment)
    }
}