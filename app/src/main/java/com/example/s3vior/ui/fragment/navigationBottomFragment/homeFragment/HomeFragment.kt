package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.home.PersonViewModel

class HomeFragment :BaseFragment<FragmentHomeBinding>
    (FragmentHomeBinding::inflate, R.layout.fragment_home), RecyclerViewInteractionListener {

    private val personViewModel: PersonViewModel by activityViewModels()

    override fun initViewModel() {
        binding.viewModel = personViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = HomeItemAdapter(mutableListOf(), this)
    }

    override fun <T> onClickItem(view: T) {
        view as Person
        Toast.makeText(requireActivity(),view.name, Toast.LENGTH_LONG).show()
    }


}
