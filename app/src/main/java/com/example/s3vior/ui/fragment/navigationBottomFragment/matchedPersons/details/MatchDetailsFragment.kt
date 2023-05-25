package com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.databinding.FragmentMatchDetailsBinding
import com.example.s3vior.ui.fragment.addPersonDetails.personDetails.AllPersonInfoFragmentArgs
import com.example.s3vior.ui.fragment.addPersonDetails.personDetails.AllPersonInfoViewModel
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.matchedPersons.MatchedPersonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MatchDetailsFragment : BaseFragment<FragmentMatchDetailsBinding>(FragmentMatchDetailsBinding::inflate){

    private val args: MatchDetailsFragmentArgs by navArgs()
    private val viewModel: MatchDetailsViewModel by viewModels()

    override fun callFunctions() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        getPersonDetails()

    }






    private fun getPersonDetails() {
        lifecycleScope.launch (Dispatchers.IO){
            viewModel.getMatchedDetails(args.matchId)
        }
    }


}