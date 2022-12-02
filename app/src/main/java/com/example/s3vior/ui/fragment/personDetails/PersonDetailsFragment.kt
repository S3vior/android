package com.example.s3vior.ui.fragment.personDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentPersonDetailsBinding


class PersonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPersonDetailsBinding

    var statuts = arrayOf("Male", "Female")
    var age = arrayOf("10-20", "20-30")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(inflater)


        spinner(statuts,binding.spinnerGender)
        spinner(age,binding.ageSpinner)
        return binding.root
    }

    private fun spinner(array: Array<String>,spinner: Spinner){
        val arrayAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

}