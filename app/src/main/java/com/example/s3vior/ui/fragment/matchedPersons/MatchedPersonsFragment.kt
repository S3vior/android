package com.example.s3vior.ui.fragment.matchedPersons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMatchedPersonsBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.HomeItemAdapter
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.PersonViewModel
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener

class MatchedPersonsFragment :
    BaseFragment<FragmentMatchedPersonsBinding>(FragmentMatchedPersonsBinding::inflate),
    RecyclerViewInteractionListener {

    private val matchedPersonViewModel: MatchedPersonsViewModel by viewModels()

    override fun callFunctions() {
        binding.viewModel = matchedPersonViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        recyclerAdapter()
    }

    override fun <T> onClickItem(view: T) {

    }
    private fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MatchedPersonAdapter(mutableListOf(), this)
    }
}