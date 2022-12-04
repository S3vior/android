package com.example.s3vior.ui.fragment.bottomFragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.recyclerView.Case
import com.example.s3vior.ui.recyclerView.HomeItemAdapter
import com.example.s3vior.ui.recyclerView.RecyclerViewInteractionListener

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {
    override fun initViewModel() {
    }

    override fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val list = listOf<Case>(
            Case(1,R.drawable.baseline_home_24, "ahmed", "benha", "2033", "afa"),
            Case(2,R.drawable.baseline_home_24, "ahmed", "benha", "2033", "afa"),
            Case(3,R.drawable.baseline_home_24, "ahmed", "benha", "2033", "afa"),
            Case(4,R.drawable.baseline_home_24, "ahmed", "benha", "2033", "afa")
        )
        binding.recyclerView.adapter = HomeItemAdapter(list, this)
    }

    override fun onClickItem() {

    }
}