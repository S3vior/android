package com.example.s3vior.ui.fragment.navigationBottomFragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.SettingData
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMoreBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.recyclerView.HomeItemAdapter
import com.example.s3vior.ui.recyclerView.RecyclerViewInteractionListener
import com.example.s3vior.ui.recyclerView.SettingItemAdapter
import com.example.s3vior.viewModels.SettingViewModel
import java.util.Collections.list

class MoreFragment :
    BaseFragment<FragmentMoreBinding>(FragmentMoreBinding::inflate, R.layout.fragment_more),
    RecyclerViewInteractionListener {

    private val settingViewModel:SettingViewModel by activityViewModels()
    override fun initViewModel() {
        binding.viewModel=settingViewModel
        binding.lifecycleOwner= viewLifecycleOwner
    }

    override fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerSetting.layoutManager = layoutManager
        binding.recyclerSetting.adapter = SettingItemAdapter(mutableListOf(), this)
    }

    override fun onClickItem(view: View) {
    }

}