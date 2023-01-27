package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment

import android.view.View
import android.widget.Toast
import androidx.databinding.adapters.ToolbarBindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMoreBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener

class MoreFragment :
    BaseFragment<FragmentMoreBinding>(FragmentMoreBinding::inflate, R.layout.fragment_more),
    RecyclerViewInteractionListener {

    private val settingViewModel: SettingViewModel by activityViewModels()
    override fun initViewModel() {
        binding.viewModel=settingViewModel
        binding.lifecycleOwner= viewLifecycleOwner
    }

    override fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerSetting.layoutManager = layoutManager

        binding.recyclerSetting.adapter = SettingItemAdapter(mutableListOf(), this)
    }

    override fun <T> onClickItem(view: T) {
        view as SettingData
        Toast.makeText(requireActivity(),view.name,Toast.LENGTH_LONG).show()
    }


}