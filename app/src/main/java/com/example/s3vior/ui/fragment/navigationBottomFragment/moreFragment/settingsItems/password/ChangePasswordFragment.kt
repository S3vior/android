package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.password

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.databinding.FragmentChangePasswordBinding
import com.example.s3vior.domain.usecases.VALID_PASSWORD_REGEX
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    override fun callFunctions() {


        binding.progressBar.visibility = View.GONE
        binding.changePassword.visibility = View.VISIBLE

        binding.changePassword.setOnClickListener {


            if (binding.oldpassword.text.isBlank()) {
                showDefaultDialog("please, enter old password")
            }

            if (binding.newpassword.text.toString() != binding.confirmnewpassword.text.toString()) {

                showDefaultDialog("your password is not matched")

            }

            if (!binding.newpassword.text.matches(
                    VALID_PASSWORD_REGEX
                ) && !binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {
                showDefaultDialog("please, enter valid password")
            }

            if (binding.oldpassword.text.isNotBlank() && binding.newpassword.text.toString() == binding.confirmnewpassword.text.toString()
                && binding.newpassword.text.matches(
                    VALID_PASSWORD_REGEX
                ) && binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {


                binding.progressBar.visibility = View.VISIBLE
                binding.changePassword.visibility = View.GONE

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val result = changePasswordViewModel.changePasswordUseCase.invoke(
                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDQyNzA1NSwianRpIjoiOTdmN2ZmNTctMWU2Ny00MWQxLTkxZDEtNWI3YWMxMDYzZjhlIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjg0NDI3MDU1LCJleHAiOjE2ODQ0NDUwNTV9.hVXgCf_X1sFOTUVxzSqh4E3lqoiy6ayuHKCy6EYEKWM",
                            PasswordChangeRequest(
                                binding.oldpassword.text.toString(),
                                binding.newpassword.text.toString(),
                                binding.confirmnewpassword.text.toString()
                            )
                        )
                        withContext(Dispatchers.Main) {
                            showDefaultDialog(result)
                            if (result == "Failed to updated Password" || result == "Password updated successfully.") {
                                binding.progressBar.visibility = View.GONE
                                binding.changePassword.visibility = View.VISIBLE
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
}


