package com.example.s3vior.ui.onBoarding.screens

import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideTwoBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment

class GuideTwoFragment :
    BaseScreenFragment<FragmentGuideTwoBinding>(FragmentGuideTwoBinding::inflate) {
    override fun initButton() {
        binding.nextGuideTwo.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 2
        }

    }

    override fun initSpinner() {

    }
}