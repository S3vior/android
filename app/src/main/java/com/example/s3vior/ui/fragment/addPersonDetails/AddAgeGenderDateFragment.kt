package com.example.s3vior.ui.fragment.addPersonDetails

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.util.rangeTo
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddAgeGenderDateBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.Constants
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.SecondDetails
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

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


    private fun callBack() {
        validateFocusListener()
        binding.nextAddDate.setOnClickListener {
            if (binding.year.text!!.isBlank()) {

                showSnackBar("من فضلك ادخل تاريخ العثور عليه")
            }
            if (binding.edDay.text!!.isBlank()) {

                showSnackBar("من فضلك ادخل  تاريخ العثور عليه")
            }
            if (binding.edAge.text!!.isBlank()) {

                showSnackBar("من فضلك ادخل عمره تقريبا")
            }

            if (binding.year.text!!.isNotBlank() && binding.edDay.text!!.isNotBlank() && binding.edAge.text!!.isNotBlank()
                && validAge() != "Invalid age" && validDay() != "Invalid day" && validYear() != "Invalid year"
            ) {
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
    }

    private fun validateFocusListener() {
        binding.year.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.yearContainer.helperText = validYear()
            }
        }
        binding.edDay.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.dayContainer.helperText = validDay()
            }
        }
        binding.edAge.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.ageContainer.helperText = validAge()
            }
        }

    }

    private fun validAge(): String? {
        val age = binding.edAge.text.toString()
        if (age !in (1..100).map { it.toString() }
        ) {
            return "Invalid age"
        }
        return null
    }


    private fun validYear(): String? {
        val year = binding.year.text.toString()
        if (year !in (2010..Calendar.getInstance().get(Calendar.YEAR)).map { it.toString() }
        ) {
            return "Invalid year"
        }
        return null
    }

    private fun validDay(): String? {
        val day = binding.edDay.text!!.toString()
        val range = 1..31
        val days = range.map { it.toString() }
        Log.d("list", days.toString() + range.toString())
        if (day !in days) {
            return "Invalid day"
        }
        return null
    }


    override fun callFunctions() {
        initSpinner()
        callBack()
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG,
        )
        // Set the max lines of SnackBar
        snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines =
            10
        snackBar.show()
    }
}