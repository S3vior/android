package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {

    private val personViewModel: PersonViewModel by activityViewModels()

    private fun initViewModel() {
        binding.viewModel = personViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = HomeItemAdapter(mutableListOf(), this)

    }

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()
    }

    override fun <T> onClickItem(view: T) {
        view as Person
        findNavController().navigate(R.id.action_homeFragment_to_allPersonInfo)
    }


}
