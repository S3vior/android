package com.example.s3vior.ui.onBoarding.screens

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideThreeBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment


class GuideThreeFragment : BaseScreenFragment<FragmentGuideThreeBinding>(FragmentGuideThreeBinding::inflate) {
    override fun initButton() {
            binding.finish.setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_addNameFragment)
//                val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
//                viewPager?.currentItem = 3
            }

    }

    override fun initSpinner() {


    }
}