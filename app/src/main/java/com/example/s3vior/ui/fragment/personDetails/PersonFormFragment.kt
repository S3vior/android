package com.example.s3vior.ui.fragment.personDetails

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PersonFormFragment : BaseFragment<FragmentPersonFormBinding>(
    FragmentPersonFormBinding::inflate
) {
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private lateinit var status: String

    @SuppressLint("UseRequireInsteadOfGet", "SetTextI18n")

    // val status = args.maqoudStatus
    private fun observeDataFromViewModel() {

        sharedViewModel.firstDetails.observe(viewLifecycleOwner) {
            binding.personName.text = it.name
            binding.personDescription.text = it.description

        }

        sharedViewModel.mafqoudStatus.observe(viewLifecycleOwner) {
            status = it
            Log.d("STATUS", it)
        }

        sharedViewModel.secondDetails.observe(viewLifecycleOwner) {
            binding.dateFindPerson.text = "${it.day}/${it.month}/${it.year}"
            binding.personAge.text = it.age
            binding.personGender.text = it.gender
        }


        sharedViewModel.thirdDetails.observe(viewLifecycleOwner) {
            binding.shapeableImageView.setImageURI(it.imageUri)
        }


    }

    private fun sendPersonData() {

        try {
            val imageUri = sharedViewModel.thirdDetails.value?.imageUri

            lifecycleScope.launch(Dispatchers.IO) {

                val result = sharedViewModel.uploadPersonUseCase.invoke(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4MzY0NjE2NSwianRpIjoiMDk0ODI0YzctMjdlZi00ODUwLWJkN2EtNzIwMGZiOTZlZjBmIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6NSwibmJmIjoxNjgzNjQ2MTY1LCJleHAiOjE2ODM2NjQxNjV9.l8wnvyhkr2Z_qEdRxHRYey3cnIFHu3OvkdADtY39S9g",
                    name = binding.personName.text.toString(),
                    age = binding.personAge.text.toString().toInt(),
                    gender = binding.personGender.text.toString(),
                    description = binding.personDescription.text.toString(),
                    type = status,
                    latitude = "30.4666648",
                    longitude = "31.1833326",
                    imageUri = imageUri!!
                )

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                }

            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }


    }

    override fun callFunctions() {

        observeDataFromViewModel()
        binding.button4.setOnClickListener {
            sendPersonData()
        }
    }


}