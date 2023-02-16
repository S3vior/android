package com.example.s3vior.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.s3vior.databinding.FragmentViewPagerBinding
import com.example.s3vior.ui.onBoarding.screens.GuideOneFragment
import com.example.s3vior.ui.onBoarding.screens.GuideThreeFragment
import com.example.s3vior.ui.onBoarding.screens.GuideTwoFragment


class ViewPagerFragment : Fragment(){

    private lateinit var binding:FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentViewPagerBinding.inflate(inflater)
        initViewPager()
        return binding.root
    }

    private fun initViewPager(){

        val fragmentList = arrayListOf<Fragment>(
            GuideOneFragment(),
            GuideTwoFragment(),
            GuideThreeFragment(),

        )

        val adapter = ViewPagerAdapter(fragmentList,childFragmentManager,viewLifecycleOwner.lifecycle)
        binding.viewPager.adapter = adapter

    }


}