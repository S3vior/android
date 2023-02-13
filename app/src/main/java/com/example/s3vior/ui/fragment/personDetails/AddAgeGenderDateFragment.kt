package com.example.s3vior.ui.fragment.personDetails

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddAgeGenderDateBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.Constants
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.SecondDetails

class AddAgeGenderDateFragment :
    BaseFragment<FragmentAddAgeGenderDateBinding>(
        FragmentAddAgeGenderDateBinding::inflate,

    ) {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private fun initSpinner() {
        initSpinner(Constants.SpinnerData.month, binding.spinnerMonth)
        initSpinner(Constants.SpinnerData.gender, binding.spinnerGender)
    }


    private fun initSpinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }


    private fun callBack(){
        binding.nextAddDate.setOnClickListener {
            sharedViewModel.getDataFromAgeGenderFragment(
                SecondDetails(
                    binding.year.text.toString(),
                    binding.spinnerMonth.selectedItem.toString(),
                    binding.edDay.text.toString(),
                    binding.edAge.text.toString(),
                    binding.spinnerGender.selectedItem.toString()
                )
            )
            Navigation.findNavController(it)
                .navigate(R.id.action_addAgeGenderDateFragment_to_addPhotoLocationFragment)

        }
    }



    override fun callFunctions() {
        initSpinner()
        callBack()
    }


}