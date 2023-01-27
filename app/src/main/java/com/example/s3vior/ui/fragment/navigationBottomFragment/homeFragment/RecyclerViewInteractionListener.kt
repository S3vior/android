package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.view.View
import android.view.ViewGroup
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

interface RecyclerViewInteractionListener:BaseInterfaceListener {
    fun onClickItem(view:View)
}