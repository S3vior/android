package com.example.s3vior.ui.fragment.navigationBottomFragment

import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAnnouncementBinding
import com.example.s3vior.ui.fragment.base.BaseFragment


class AnnouncementFragment :
    BaseFragment<FragmentAnnouncementBinding>(FragmentAnnouncementBinding::inflate,R.layout.fragment_announcement) {
    override fun initViewModel() {

    }

    override fun recyclerAdapter() {

    }

    override fun onResume() {
        super.onResume()
            binding.button2.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_announcementFragment_to_viewPagerFragment)

            }

    }


}