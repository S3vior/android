package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.contact_us

import android.app.AlertDialog
import android.os.Handler
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.data.source.remote.endPoints.ContactUs
import com.example.s3vior.databinding.FragmentContactUsBinding
import com.example.s3vior.domain.usecases.VALID_PASSWORD_REGEX
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.BtnLoadingProgressbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ContactUsFragment :
    BaseFragment<FragmentContactUsBinding>(FragmentContactUsBinding::inflate) {
    private val handler = Handler()

    private val contactUsViewModel: ContactUsViewModel by viewModels()
    override fun callFunctions() {

        sendProblem()
    }

    private fun sendProblem() {
        emailFocusListener()
        binding.activityMainBtn.setOnClickListener {


            if (binding.issueMessage.text!!.isNotBlank() && binding.gmail.text!!.isNotBlank() && isValidGmailAddress(
                    binding.gmail.text.toString().trim()
                ) && binding.issueMessage.text!!.isNotBlank() && binding.issueMessage.text!!.length >= 30
            ) {
                val progressbar = BtnLoadingProgressbar(it) // `it` is view of button
                progressbar.setLoading()

                try {

                    lifecycleScope.launch(Dispatchers.IO) {
                        val result = contactUsViewModel.contactUsUseCase.invoke(
                            token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDY2MzI0MywianRpIjoiNDQ4ZGM4YmEtMWMwMy00NGEzLWJlMjQtZDEyNGRmMmYxZjViIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MSwibmJmIjoxNjg0NjYzMjQzLCJsb2dnZWRfb3V0IjpmYWxzZX0.WYQ2RGpVCvtkPatmB_fVkIz2XEkJYIZfkfXkVScJmlY",
                            ContactUs(
                                name = binding.name.text.toString(),
                                email = binding.gmail.text.toString(),
                                problem = binding.issueMessage.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            if (result == "problem send successfully" /*|| result == "something error"*/) {

                                progressbar.setState(true) {
                                    lifecycleScope.launch {
                                        delay(800)
                                    }
                                    progressbar.reset()
                                }// executed after animation end


                            }
                            if (result == "something wrong") {
                                startError(progressbar)

                            }
                            //   showDefaultDialog(result)

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

        alertDialogBuilder.setTitle("تواصل معنا")
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

    private fun startError(progressbar: BtnLoadingProgressbar) {
        progressbar.reset()
        handler.postDelayed({
            progressbar.setLoading()
            handler.postDelayed({
                progressbar.setState(false) { // executed after animation end
                    handler.postDelayed({
                        progressbar.reset()
                    }, 1500)
                }
            }, 2000)
        }, 600)
    }

    private fun isValidGmailAddress(email: String): Boolean {
        val gmailPattern = Patterns.EMAIL_ADDRESS // Built-in pattern for email validation

        // Additional regex pattern for Gmail address validation
        val gmailRegex = Regex("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@gmail\\.com$")

        return gmailPattern.matcher(email).matches() && gmailRegex.matches(email)
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

    private fun emailFocusListener() {
        binding.gmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
        binding.name.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validName()
            }
        }
        binding.issueMessage.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneContainer.helperText = validProblem()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.gmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun validName(): String? {
        val emailText = binding.name.text.toString()
        if (emailText.length < 3) {
            return "Invalid Name"
        }
        return null
    }

    private fun validProblem(): String? {
        val emailText = binding.issueMessage.text.toString()
        return if (emailText.length < 30) {
            "Problem must be more than 30 characters"
        } else {
            ""
        }
    }
}