package com.example.s3vior.ui.fragment.bottomFragment

import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAnnouncementBinding
import com.example.s3vior.ui.fragment.base.BaseFragment


class AnnouncementFragment :
    BaseFragment<FragmentAnnouncementBinding>(FragmentAnnouncementBinding::inflate) {
    override fun initViewModel() {

    }

    override fun recyclerAdapter() {
        binding.button2.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_mainFragment_to_personDetailsFragment
            )
        }
    }


}