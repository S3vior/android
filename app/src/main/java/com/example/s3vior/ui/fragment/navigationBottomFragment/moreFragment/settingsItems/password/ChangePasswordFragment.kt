package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.password

import android.app.AlertDialog
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.databinding.FragmentChangePasswordBinding
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
class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {
    private val handler = Handler()

    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    override fun callFunctions() {
        emailFocusListener()

        binding.activityMainBtn.setOnClickListener {


            if (binding.oldpassword.text!!.isBlank()) {
       //         showDefaultDialog("please, enter old password")
            }





            if (!binding.newpassword.text!!.matches(
                    VALID_PASSWORD_REGEX
                ) && !binding.confirmnewpassword.text!!.matches(VALID_PASSWORD_REGEX)
            ) {
//                showDefaultDialog("please, enter valid password")
            }

            if (binding.oldpassword.text!!.isNotBlank() && binding.newpassword.text.toString() == binding.confirmnewpassword.text.toString()
                && binding.newpassword.text!!.matches(
                    VALID_PASSWORD_REGEX
                ) && binding.confirmnewpassword.text!!.matches(VALID_PASSWORD_REGEX)
            ) {
                val progressbar = BtnLoadingProgressbar(it) // `it` is view of button
                progressbar.setLoading()

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val result = changePasswordViewModel.changePasswordUseCase.invoke(
                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDY2MzI0MywianRpIjoiNDQ4ZGM4YmEtMWMwMy00NGEzLWJlMjQtZDEyNGRmMmYxZjViIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MSwibmJmIjoxNjg0NjYzMjQzLCJsb2dnZWRfb3V0IjpmYWxzZX0.WYQ2RGpVCvtkPatmB_fVkIz2XEkJYIZfkfXkVScJmlY",
                            PasswordChangeRequest(
                                binding.oldpassword.text.toString(),
                                binding.newpassword.text.toString(),
                                binding.confirmnewpassword.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            //         showDefaultDialog(result)
                            if (result == "Password updated successfully.") {
                                progressbar.setState(true) {
                                    lifecycleScope.launch {
                                        delay(800)
                                    }
                                    progressbar.reset()
                                }
                            } else {
                                startError(progressbar)
                            }
                            if (result == "Failed to updated Password") {
                                startError(progressbar)
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            showDefaultDialog(e.message.toString())
                        }
                    }


                }
            }

        }
    }

    private fun showDefaultDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setTitle("change password")
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

    private fun emailFocusListener() {
        binding.oldpassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validOldPassword()
            }
        }
        binding.confirmnewpassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneContainer.helperText = validateMatching()
            }
        }
        binding.newpassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validNewPassword()
            }
        }

    }

    private fun validateMatching(): String? {
        return if (binding.newpassword.text!!.toString() != binding.confirmnewpassword.text.toString()) {
            "password not matched"
        } else null
    }

    private fun validOldPassword(): String? {
        return if (binding.oldpassword.text!!.toString().length < 3) {
            "invalid password"
        } else null
    }

    private fun validConfirmPassword():  String? {
        return if (!binding.confirmnewpassword.text!!.matches(
                VALID_PASSWORD_REGEX
            )
        ) {
            "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character."
        } else null
    }

    private fun validNewPassword(): String? {
        return if (!binding.newpassword.text!!.matches(
                VALID_PASSWORD_REGEX
            )
        ) {
            "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character."
        } else null
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
}


