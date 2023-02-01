package com.example.s3vior.ui.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddNameBinding
import com.example.s3vior.databinding.FragmentGuideOneBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment

class AddNameFragment :
    BaseScreenFragment<FragmentAddNameBinding>(FragmentAddNameBinding::inflate) {
    override fun initButton() {

        binding.nextAddName.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 4
        }
    }

    override fun initSpinner() {

    }

}