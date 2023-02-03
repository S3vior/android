package com.example.s3vior.ui.onBoarding.screens

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideThreeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment


class GuideThreeFragment : BaseFragment<FragmentGuideThreeBinding>(
    FragmentGuideThreeBinding::inflate
) {
    override fun callFunctions() {
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_addNameFragment)
        }
    }
}