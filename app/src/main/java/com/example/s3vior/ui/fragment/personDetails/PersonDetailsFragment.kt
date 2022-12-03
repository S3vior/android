package com.example.s3vior.ui.fragment.personDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.ui.sheetFragment.BottomSheetFragment
import com.example.s3vior.utils.Constants


class PersonDetailsFragment : Fragment() {


    private lateinit var binding: FragmentPersonDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(inflater)

        startFragment()

        return binding.root
    }

    private fun startFragment() {
        spinner()
        bottomSheet()
    }


    private fun spinner() {
        initSpinner(Constants.SpinnerData.statuts, binding.spinnerGender)
        initSpinner(Constants.SpinnerData.age, binding.ageSpinner)
    }

    private fun initSpinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    private fun bottomSheet() {
        binding.addPhoto.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(binding)
            bottomSheetFragment.show(
                this.requireActivity().supportFragmentManager, "bottomSheetDialog"
            )
        }
    }

}