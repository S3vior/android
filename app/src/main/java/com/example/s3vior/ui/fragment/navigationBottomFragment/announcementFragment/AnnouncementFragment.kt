package com.example.s3vior.ui.fragment.navigationBottomFragment.announcementFragment

import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAnnouncementBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.MafqoudStatus


class AnnouncementFragment :
    BaseFragment<FragmentAnnouncementBinding>(FragmentAnnouncementBinding::inflate) {


    override fun callFunctions() {
        binding.button2.setOnClickListener {
            val action =
                AnnouncementFragmentDirections.actionAnnouncementFragmentToViewPagerFragment(
                    "missed"
                )
            Navigation.findNavController(it).navigate(action)
        }
        binding.button.setOnClickListener {
            val action =
                AnnouncementFragmentDirections.actionAnnouncementFragmentToViewPagerFragment(
                    "founded"
                )
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun callBack() {

    }


}