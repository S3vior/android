package com.example.s3vior.ui.onBoarding.screens

import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentGuideOneBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment


class GuideOneFragment : BaseScreenFragment<FragmentGuideOneBinding>(
    FragmentGuideOneBinding::inflate,
) {

    override fun initButton() {
        binding.nextGuideOne.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem =1
        }
    }

}