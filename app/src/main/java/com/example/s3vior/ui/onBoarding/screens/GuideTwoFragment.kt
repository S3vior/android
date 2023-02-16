package com.example.s3vior.ui.onBoarding.screens

import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideTwoBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.base.ViewPagerInstance

class GuideTwoFragment :
    BaseFragment<FragmentGuideTwoBinding>(
        FragmentGuideTwoBinding::inflate

    ) {


    override fun callFunctions() {
        binding.nextGuideTwo.setOnClickListener {
            ViewPagerInstance.callFunction(2, requireActivity())

        }
    }
}