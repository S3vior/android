package com.example.s3vior.ui.fragment.addPersonDetails

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import com.google.android.material.snackbar.Snackbar
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
            binding.progressBar.visibility = View.VISIBLE
            binding.button4.visibility = View.GONE
            lifecycleScope.launch(Dispatchers.IO) {

                val result = sharedViewModel.uploadPersonUseCase.invoke(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDU3NjI5MiwianRpIjoiYmE2OGNmZDktYTQ4NC00YTE3LWE1OTUtNGI5Y2E4MGY0MWY5IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjg0NTc2MjkyLCJleHAiOjE2ODQ1OTQyOTJ9.B00MpP9tuEZWXBLkyCqjDvO8Byxwe2ohadrYz--UqlQ",
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
                    //     showSnackBar(result)
                    showDefaultDialog(result)
                    if (result == "Person uploaded" || result == "Person not uploaded") {
                        binding.progressBar.visibility = View.GONE
                        binding.button4.visibility = View.VISIBLE
                    }

                }

            }

        } catch (e: Exception) {

            showDefaultDialog(e.message.toString())
        }


    }

    private fun showDefaultDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setTitle("Dialog Title")
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("تم") { dialog, which ->
            dialog.dismiss()
           Navigation.findNavController(requireView()).navigate(R.id.action_personFormFragment_to_announcementFragment)

        }

        alertDialogBuilder.setNegativeButton("ارسال من جديد") { dialog, which ->

            dialog.dismiss()
            Navigation.findNavController(requireView()).navigate(R.id.action_personFormFragment_to_addNameFragment)

        }

        alertDialogBuilder.setOnCancelListener {
            // Handle dialog cancellation
            // You can perform an action here or dismiss the dialog
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun callFunctions() {

        observeDataFromViewModel()
        binding.progressBar.visibility = View.GONE
        binding.button4.setOnClickListener {
            sendPersonData()
        }
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