package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import androidx.fragment.app.activityViewModels
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment
import com.example.s3vior.viewModel.SharedViewModel


class PersonFormFragment : BaseScreenFragment<FragmentPersonFormBinding>(
    FragmentPersonFormBinding::inflate
) {
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun initButton() {

        sharedViewModel.firstDetails.observe(viewLifecycleOwner){
            binding.textView19.text = it.name
            binding.textView20.text = it.description
        }
        sharedViewModel.secondDetails.observe(viewLifecycleOwner){
            binding.dateFindPerson.text = "${it.day}/${it.month}/${it.year}"

            binding.textView24.text = it.age
            binding.textView25.text =it.gender
        }


    }

    override fun initSpinner() {

    }

}