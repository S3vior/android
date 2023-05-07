package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment

import android.view.View
import android.widget.Toast
import androidx.databinding.adapters.ToolbarBindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMoreBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener

class MoreFragment :
    BaseFragment<FragmentMoreBinding>(FragmentMoreBinding::inflate),
    RecyclerViewInteractionListener {

    private val settingViewModel: SettingViewModel by activityViewModels()
    fun initViewModel() {
        binding.viewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerSetting.layoutManager = layoutManager

        binding.recyclerSetting.adapter = SettingItemAdapter(mutableListOf(), this)
    }

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()
    }

    override fun <T> onClickItem(view: T) {

        view as SettingData
        when (view.id) {
            1 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_changePasswordFragment)
            }

            2 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_notificationsFragment)

            }

            3 -> {
                Navigation.findNavController(requireView()).navigate(
                    R.id.action_moreFragment_to_matchedPersonsFragment
                )
            }
            4 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_FQAFragment)
            }

            5 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_contactUsFragment)
            }

            6 -> {}
            7 -> {}

        }
    }


}