package com.example.s3vior.ui.fragment.personDetails

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddAgeGenderDateBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.onBoarding.screens.base.BaseScreenFragment
import com.example.s3vior.utils.Constants
import com.example.s3vior.viewModel.model.SecondDetails
import com.example.s3vior.viewModel.SharedViewModel

class AddAgeGenderDateFragment :
    BaseFragment<FragmentAddAgeGenderDateBinding>(FragmentAddAgeGenderDateBinding::inflate,R.layout.fragment_add_age_gender_date) {
    private val sharedViewModel: SharedViewModel by activityViewModels()



      fun initSpinner() {
        initSpinner(Constants.SpinnerData.month, binding.spinnerMonth)
        initSpinner(Constants.SpinnerData.gender, binding.spinnerGender)

    }


    private fun initSpinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun initViewModel() {
        initSpinner()
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
            Navigation.findNavController(it).navigate(R.id.action_addAgeGenderDateFragment_to_personFormFragment)

        }

    }

    override fun recyclerAdapter() {

    }
}