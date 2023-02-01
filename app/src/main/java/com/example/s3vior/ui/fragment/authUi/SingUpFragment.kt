package com.example.s3vior.ui.fragment.authUi

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentSingUpBinding
import com.example.s3vior.databinding.NewSignupFragmentBinding

class SingUpFragment : Fragment() {
    private lateinit var binding: NewSignupFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.new_signup_fragment, container, false)

        callBacks()
        return binding.root
    }

    private fun callBacks() {
        binding.tvLoginIn.setOnClickListener { view ->
            Navigation.findNavController(view)
                .navigate(R.id.action_singUpFragment_to_signInFragment)

        }
    }

}