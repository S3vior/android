package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.password

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.databinding.FragmentChangePasswordBinding
import com.example.s3vior.domain.usecases.VALID_PASSWORD_REGEX
import com.example.s3vior.ui.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    override fun callFunctions() {
        binding.lifecycleOwner = viewLifecycleOwner


        binding.changePassword.setOnClickListener {
            if (binding.oldpassword.text.isBlank()) {
                Toast.makeText(context, "please, enter old password", Toast.LENGTH_LONG).show()
            }

            if (binding.newpassword.text.toString() != binding.confirmnewpassword.text.toString()) {
                Toast.makeText(context, "your password is not matched", Toast.LENGTH_LONG)
                    .show()

            }

            if (!binding.newpassword.text.matches(
                    VALID_PASSWORD_REGEX
                ) && !binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {
                Toast.makeText(context, "please, enter valid password", Toast.LENGTH_LONG)
                    .show()

            }

            if (binding.oldpassword.text.isNotBlank() && binding.newpassword.text.toString() == binding.confirmnewpassword.text.toString()
//                &&
//                binding.newpassword.text.matches(
//                    VALID_PASSWORD_REGEX
//                ) && binding.confirmnewpassword.text.matches(VALID_PASSWORD_REGEX)
            ) {
                lifecycleScope.launch {
                    val a = changePasswordViewModel.changePasswordUseCase.invoke(
                        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4MzU1NDc4MiwianRpIjoiYTc5ZDEyMjItYzEzYi00NmRmLWFmOWMtODViZGVkY2FjMzZjIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6NSwibmJmIjoxNjgzNTU0NzgyLCJleHAiOjE2ODM1NzI3ODJ9.PkY-ItQK3TMr4YhSmiBVJPQVvsBBMK8xGyAWz1p5zU4",
                        binding.oldpassword.text.toString(),
                        binding.newpassword.text.toString(),
                        binding.confirmnewpassword.text.toString()
                    )
                    if (a == "Old password is incorrect.") {
                        Toast.makeText(context, "Old password is incorrect.", Toast.LENGTH_LONG).show()
                    }
                    if (a == "New password and confirmation do not match.") {
                        Toast.makeText(context, "New password and confirmation do not match.", Toast.LENGTH_LONG).show()
                    }
                    if (a == "Password updated successfully.") {
                        Toast.makeText(context, "Password updated successfully.", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }

    }


}


