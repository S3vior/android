package com.example.s3vior.ui.fragment.personDetails

import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddNameBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.FirstDetails

class AddNameFragment :
    BaseFragment<FragmentAddNameBinding>(FragmentAddNameBinding::inflate ) {

    private val sharedViewModel:SharedViewModel by activityViewModels()


     private fun callBack(){
         binding.nextAddName.setOnClickListener {
             sharedViewModel.getDataFromNameFragment(FirstDetails(binding.edCaseName.text.toString(),binding.editTextTextPersonName3.text.toString()))
             Navigation.findNavController(it).navigate(R.id.action_addNameFragment_to_addAgeGenderDateFragment)
         }
     }

    override fun callFunctions() {
        callBack()
    }


}