package com.example.s3vior.ui.fragment.addPersonDetails

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentPersonFormBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.BtnLoadingProgressbar
import com.example.s3vior.viewModel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PersonFormFragment : BaseFragment<FragmentPersonFormBinding>(
    FragmentPersonFormBinding::inflate
) {
    private val sharedViewModel: SharedViewModel by activityViewModels()


    private lateinit var status: String
    private val handler = android.os.Handler()

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

    private fun sendPersonData(view: View) {

        try {
            val imageUri = sharedViewModel.thirdDetails.value?.imageUri
            val progressbar = BtnLoadingProgressbar(view) // `it` is view of button
            progressbar.setLoading()
            lifecycleScope.launch(Dispatchers.IO) {

                val result = sharedViewModel.uploadPersonUseCase.invoke(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY4NDY2MzI0MywianRpIjoiNDQ4ZGM4YmEtMWMwMy00NGEzLWJlMjQtZDEyNGRmMmYxZjViIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MSwibmJmIjoxNjg0NjYzMjQzLCJsb2dnZWRfb3V0IjpmYWxzZX0.WYQ2RGpVCvtkPatmB_fVkIz2XEkJYIZfkfXkVScJmlY",
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
//                    showDefaultDialog(result)
                    if (result == "Person uploaded") {
                        progressbar.setState(true) {

                            progressbar.reset()
                            lifecycleScope.launch {
                                delay(1500)
                            }
                            Navigation.findNavController(requireView())
                                .navigate(R.id.action_personFormFragment_to_announcementFragment)

                        }


                    } else {
                        startError(progressbar)
                    }
                    if (result == "Person not uploaded") {
                        startError(progressbar)
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
            Navigation.findNavController(requireView())
                .navigate(R.id.action_personFormFragment_to_announcementFragment)

        }

        alertDialogBuilder.setNegativeButton("ارسال من جديد") { dialog, which ->

            dialog.dismiss()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_personFormFragment_to_addNameFragment)

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

        binding.button4.setOnClickListener {
            sendPersonData(it)
        }
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