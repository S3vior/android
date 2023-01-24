package com.example.s3vior.ui.onBoarding.screens

import androidx.navigation.fragment.findNavController
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideThreeBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment


class GuideThreeFragment : BaseScreenFragment<FragmentGuideThreeBinding>(FragmentGuideThreeBinding::inflate) {
    override fun initButton() {
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_personDetailsFragment)
        }

    }
}