package com.example.s3vior.ui.onBoarding.screens

import com.example.s3vior.databinding.FragmentGuideOneBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.base.ViewPagerInstance


class GuideOneFragment : BaseFragment<FragmentGuideOneBinding>(
    FragmentGuideOneBinding::inflate
) {
    override fun callFunctions() {
        binding.nextGuideOne.setOnClickListener {
            ViewPagerInstance.callFunction(1,  requireActivity())
        }
    }
}