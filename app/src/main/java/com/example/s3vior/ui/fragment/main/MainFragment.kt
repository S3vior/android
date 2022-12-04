package com.example.s3vior.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMainBinding

class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val navController = activity?.findNavController(R.id.fragment_host)
        binding.bottomNavigationView.setupWithNavController(navController!!)
    }


}