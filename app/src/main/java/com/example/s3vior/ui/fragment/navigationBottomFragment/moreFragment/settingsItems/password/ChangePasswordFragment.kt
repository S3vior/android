package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.password

import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.data.source.remote.endPoints.PasswordChangeRequest
import com.example.s3vior.databinding.FragmentChangePasswordBinding
import com.example.s3vior.domain.usecases.VALID_PASSWORD_REGEX
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val changePasswordViewModel: ChangePasswordViewModel by activityViewModels()
    override fun callFunctions() {


        binding.changePassword.setOnClickListener {


            if (binding.oldpassword.text.isBlank()) {
                showSnackBar("please, enter old password")
            }

            if (binding.newpassword.text.toString() != binding.confirmnewpassword.text.toString()) {

                showSnackBar("your password is not matched")

            }

            if (!binding.newpassword.text.matches(
                    VALID_PASSWORD_REGEX
                ) && !binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {
                showSnackBar("please, enter valid password")
            }

            if (binding.oldpassword.text.isNotBlank() && binding.newpassword.text.toString() == binding.confirmnewpassword.text.toString()
                && binding.newpassword.text.matches(
                    VALID_PASSWORD_REGEX
                ) && binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val a = changePasswordViewModel.changePasswordUseCase.invoke(
                        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDQyNzA1NSwianRpIjoiOTdmN2ZmNTctMWU2Ny00MWQxLTkxZDEtNWI3YWMxMDYzZjhlIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MiwibmJmIjoxNjg0NDI3MDU1LCJleHAiOjE2ODQ0NDUwNTV9.hVXgCf_X1sFOTUVxzSqh4E3lqoiy6ayuHKCy6EYEKWM",
                        PasswordChangeRequest(
                            binding.oldpassword.text.toString(),
                            binding.newpassword.text.toString(),
                            binding.confirmnewpassword.text.toString()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        showSnackBar(a)
                    }

                }
            }

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


