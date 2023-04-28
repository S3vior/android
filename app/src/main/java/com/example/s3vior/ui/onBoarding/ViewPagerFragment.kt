package com.example.s3vior.ui.onBoarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.s3vior.databinding.FragmentViewPagerBinding
import com.example.s3vior.ui.onBoarding.screens.GuideOneFragment
import com.example.s3vior.ui.onBoarding.screens.GuideThreeFragment
import com.example.s3vior.ui.onBoarding.screens.GuideTwoFragment
import com.example.s3vior.viewModel.SharedViewModel


class ViewPagerFragment : Fragment(){

    private lateinit var binding:FragmentViewPagerBinding
    private val args : ViewPagerFragmentArgs by navArgs()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentViewPagerBinding.inflate(inflater)
        initViewPager()
        sharedViewModel.getMafqoudStatus(args.mafqoudStatus)
      //  Log.d("albvioabsv", "onCreateView: ${args.mafqoudStatus}")
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