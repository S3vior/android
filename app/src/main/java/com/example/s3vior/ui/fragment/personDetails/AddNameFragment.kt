package com.example.s3vior.ui.fragment.personDetails

import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddNameBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment
import com.example.s3vior.viewModel.model.FirstDetails
import com.example.s3vior.viewModel.SharedViewModel

class AddNameFragment :
    BaseFragment<FragmentAddNameBinding>(FragmentAddNameBinding::inflate,R.layout.fragment_add_name) {

    private val sharedViewModel:SharedViewModel by activityViewModels()



    override fun initViewModel() {
        binding.nextAddName.setOnClickListener {
            sharedViewModel.getDataFromNameFragment(FirstDetails(binding.edCaseName.text.toString(),binding.editTextTextPersonName3.text.toString()))
          Navigation.findNavController(it).navigate(R.id.action_addNameFragment_to_addAgeGenderDateFragment)
        }
    }

    override fun recyclerAdapter() {

    }


}