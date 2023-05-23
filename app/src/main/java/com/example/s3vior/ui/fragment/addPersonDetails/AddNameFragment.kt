package com.example.s3vior.ui.fragment.addPersonDetails

import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAddNameBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import com.example.s3vior.viewModel.model.FirstDetails
import com.google.android.material.snackbar.Snackbar

class AddNameFragment :
    BaseFragment<FragmentAddNameBinding>(FragmentAddNameBinding::inflate) {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private fun callBack() {

        validateFocusListener()
        binding.nextAddName.setOnClickListener {
            if (binding.edCaseName.text!!.isBlank()) {

                showSnackBar("من فضلك ادخل اسم المفقود")
            }
            if (binding.editTextTextPersonName3.text!!.isBlank()) {

                showSnackBar("من فضلك ادخل مواصفات المفقود")
            }
            if (binding.editTextTextPersonName3.text!!.isNotBlank() && binding.edCaseName.text!!.isNotBlank()
                && binding.editTextTextPersonName3.text!!.length >= 20 && binding.edCaseName.text!!.length >= 2
            ) {
                try {
                    sharedViewModel.getDataFromNameFragment(
                        FirstDetails(
                            binding.edCaseName.text.toString(),
                            binding.editTextTextPersonName3.text.toString()
                        )
                    )
                    Navigation.findNavController(it)
                        .navigate(R.id.action_addNameFragment_to_addAgeGenderDateFragment)
                } catch (e: Exception) {
                    showSnackBar(e.message.toString())
                }


            }
        }

    }

    private fun validateFocusListener() {
        binding.edCaseName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nameContainer.helperText = validName()
            }
        }
        binding.editTextTextPersonName3.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.descContainer.helperText = validDesc()
            }
        }

    }

    private fun validName(): String? {
        val name = binding.edCaseName.text.toString()
        if (name.length < 2) {
            return "Invalid Name"
        }
        return null
    }

    private fun validDesc(): String {
        val desc = binding.editTextTextPersonName3.text.toString()
        return if (desc.length < 20) {
            "description must be more than 20 characters"
        } else {
            ""
        }
    }



    override fun callFunctions() {
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