package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.contact_us

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.databinding.FragmentContactUsBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ContactUsFragment :
    BaseFragment<FragmentContactUsBinding>(FragmentContactUsBinding::inflate) {

    private val contactUsViewModel: ContactUsViewModel by viewModels()
    override fun callFunctions() {
        binding.progressBar.visibility = View.GONE
        binding.sendProblem.visibility = View.VISIBLE
        sendProblem()
    }

    private fun sendProblem() {


        binding.sendProblem.setOnClickListener {
            if (binding.name.text.isBlank()) {
                showDefaultDialog("من فضلك ادخل اسمك")
            }
            if (binding.gmail.text.isBlank()) {
                showDefaultDialog("من فضلك ادخل  البريد الالكتروني")
            }
            if (binding.issueMessage.text.isBlank()) {
                showDefaultDialog("من فضلك ادخل المشكلة")
            }

            if (binding.issueMessage.text.isNotBlank() && binding.gmail.text.isNotBlank() && binding.issueMessage.text.isNotBlank()) {


                try {

                    lifecycleScope.launch(Dispatchers.IO) {
                        val result = contactUsViewModel.contactUsUseCase.invoke(
                            token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDU3NjI5MiwianRpIjoiYmE2OGNmZDktYTQ4NC00YTE3LWE1OTUtNGI5Y2E4MGY0MWY5IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjg0NTc2MjkyLCJleHAiOjE2ODQ1OTQyOTJ9.B00MpP9tuEZWXBLkyCqjDvO8Byxwe2ohadrYz--UqlQ",
                            ContactUs(
                                name = binding.name.text.toString(),
                                email = binding.gmail.text.toString(),
                                problem = binding.issueMessage.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            if (result == "problem send successfully" || result == "something error") {

                            }
                            showDefaultDialog(result)

                        }
                    }
                } catch (e: Exception) {
                    showSnackBar(e.message.toString())
                }

            }
        }
    }

    private fun showDefaultDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setTitle("contact us")
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("تم") { dialog, which ->
            dialog.dismiss()
//            Navigation.findNavController(requireView()).navigate(R.id.action_personFormFragment_to_announcementFragment)

        }

        alertDialogBuilder.setNegativeButton("ارسال من جديد") { dialog, which ->

            dialog.dismiss()
//            Navigation.findNavController(requireView()).navigate(R.id.action_personFormFragment_to_addNameFragment)

        }

        alertDialogBuilder.setOnCancelListener {
            // Handle dialog cancellation
            // You can perform an action here or dismiss the dialog
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
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