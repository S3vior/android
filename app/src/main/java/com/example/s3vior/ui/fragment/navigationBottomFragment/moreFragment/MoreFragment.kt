package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment

import android.content.Intent
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
    private fun initViewModel() {
        binding.viewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerSetting.layoutManager = layoutManager

        binding.recyclerSetting.adapter = SettingItemAdapter(mutableListOf(), this)
    }

    private fun shareApp(){
        val msg = "Discover our powerful app utilizing facial recognition technology to locate missing individuals. Join us in this groundbreaking effort by downloading the app today. " +
                "Together, we can leverage the power of facial recognition to bring missing people back to their loved ones. Make a difference and help us reunite families! " +
                "https://play.google.com/store/apps/details?id=${requireContext().packageName}"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,msg)
        intent.type = "text/plain"
        startActivity(intent)
    }

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()

        binding.support.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_moreFragment_to_contactUsFragment)
        }
        binding.notification.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_moreFragment_to_notificationsFragment)
        }
    }

    override fun <T> onClickItem(view: T) {

        view as SettingData
        when (view.id) {
            1 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_changePasswordFragment)
            }

            2 -> {
                shareApp()
            }

            3 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_FQAFragment)
            }

            4 -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_moreFragment_to_conditionFragment)
            }


        }
    }


}