package com.example.s3vior.ui.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddAgeGenderDateBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment
import com.example.s3vior.utils.Constants

class AddAgeGenderDateFragment :
    BaseScreenFragment<FragmentAddAgeGenderDateBinding>(FragmentAddAgeGenderDateBinding::inflate) {
    override fun initButton() {
        binding.nextAddDate.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 5
        }
    }

    override fun initSpinner() {
        initSpinner(Constants.SpinnerData.month, binding.spinnerMonth)
        initSpinner(Constants.SpinnerData.gender, binding.spinnerGender)

    }


    private fun initSpinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }
}