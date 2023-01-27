package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.ui.sheetFragment.BottomSheetFragment
import com.example.s3vior.utils.Constants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class PersonDetailsFragment : Fragment() {


    private lateinit var binding: FragmentPersonDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(inflater)

        startFragment()
        binding.editTextTextPersonName2.setOnClickListener {
            initCalendar()
        }

        return binding.root
    }

    private fun startFragment() {
        spinner()
        bottomSheet()
        callBack()
    }


    private fun initCalendar() {
        val datePicker = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            datePicker[Calendar.YEAR] = year
            datePicker[Calendar.MONTH] = month
            datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth

            val dateFormat = "dd-MMMM-yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
            binding.editTextTextPersonName2.text = simpleDateFormat.format(datePicker.time)
        }

        DatePickerDialog(
            requireActivity(), date, datePicker[Calendar.YEAR],
            datePicker[Calendar.MONTH],
            datePicker[Calendar.DAY_OF_MONTH],
        ).show()


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

    private fun callBack() {
        binding.categoryName.onRightDrawableClicked {
            Navigation.findNavController(it).popBackStack()
        }

    }


    @SuppressLint("ClickableViewAccessibility")
    private fun TextView.onRightDrawableClicked(onClicked: (view: TextView) -> Unit) {
        this.setOnTouchListener { v, event ->
            var hasConsumed = false
            if (v is TextView) {
                if (event.x >= v.width - v.totalPaddingRight) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        onClicked(this)
                    }
                    hasConsumed = true
                }
            }
            hasConsumed
        }
    }

}