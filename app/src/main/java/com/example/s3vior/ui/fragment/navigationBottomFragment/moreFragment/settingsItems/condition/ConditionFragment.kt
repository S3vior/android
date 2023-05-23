package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.condition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentConditionBinding
import com.example.s3vior.ui.fragment.base.BaseFragment

class ConditionFragment :
    BaseFragment<FragmentConditionBinding>(FragmentConditionBinding::inflate) {
    override fun callFunctions() {
         binding.condition.text =  requireActivity().getString(R.string.condition)
        binding.button6.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_conditionFragment_to_moreFragment)
        }
        binding.button7.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_conditionFragment_to_moreFragment)
        }
    }

}