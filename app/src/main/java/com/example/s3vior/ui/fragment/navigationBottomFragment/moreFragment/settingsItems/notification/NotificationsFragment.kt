package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentNotificationsBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.HomeItemAdapter
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener


class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) ,
    RecyclerViewInteractionListener {
    override fun callFunctions() {
        recyclerAdapter()
    }
    private fun recyclerAdapter() {
        val list= listOf(
            NotificationContent("Ahmed","Hello"),
            NotificationContent("Ahmed","Hello"),
            NotificationContent("Ahmed","Hello"),
            NotificationContent("Ahmed","Hello"),
            NotificationContent("Ahmed","Hello"),
            NotificationContent("Ahmed","Hello"),
        )

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = NotificationAdapter(list, this)

    }

    override fun <T> onClickItem(view: T) {
        view as NotificationContent
    }
}