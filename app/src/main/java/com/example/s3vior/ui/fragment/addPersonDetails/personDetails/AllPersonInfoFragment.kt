package com.example.s3vior.ui.fragment.addPersonDetails.personDetails

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
 
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.s3vior.databinding.FragmentAllPersonInfoBinding
import com.example.s3vior.ui.fragment.addPersonDetails.personDetails.AllPersonInfoFragmentArgs
import com.example.s3vior.ui.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale


@AndroidEntryPoint
class AllPersonInfoFragment : BaseFragment<FragmentAllPersonInfoBinding>(
    FragmentAllPersonInfoBinding::inflate
) {

    private val args: AllPersonInfoFragmentArgs by navArgs()
    private val viewModel: AllPersonInfoViewModel by viewModels()


    override fun callFunctions() {
        initViewModel()
        getPersonDetails()
 

        binding.btnBackToHome.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
 
        showOnMap()
        backToHomeFragment()
 
    }

    private fun showOnMap() {
        binding.btnShowonmap.setOnClickListener {
           val action= AllPersonInfoFragmentDirections.actionAllPersonInfoToMapsFragment(args.personId)

            Navigation.findNavController(it)
                .navigate(action)
        }
    }

    private fun getPersonDetails() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getPersonDetails(args.personId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(
            android.R
                .transition.slide_left
        )
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun backToHomeFragment() {
        binding.btnBackToHome.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(com.example.s3vior.R.id.action_allPersonInfo_to_homeFragment)
        }
    }
}
