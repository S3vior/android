package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.fqa


import com.example.s3vior.R
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class FqaAdapter(
    list: List<FqaContent>,
    listener: BaseInterfaceListener,
    override val layoutId: Int = R.layout.item_faq
) :
    BaseAdapter<FqaContent>(list, listener) {
}