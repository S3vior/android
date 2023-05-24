package com.example.s3vior.ui.fragment.scrapedPersons

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.data.source.remote.responseModels.ScrapedPersonsResponseItem
import com.example.s3vior.databinding.FragmentScrapedBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.HomeItemAdapter
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScrapedFragment : BaseFragment<FragmentScrapedBinding>(FragmentScrapedBinding::inflate),
    RecyclerViewInteractionListener {

    private val viewModel: ScrapedViewModel by viewModels()

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()
    }


    private fun initViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ScrapedItemAdapter(mutableListOf(), this)

    }

    override fun <T> onClickItem(view: T) {
        view as ScrapedPersonsResponseItem
    }
}