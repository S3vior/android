package com.example.s3vior.ui.recyclerView

import com.example.SettingData
import com.example.s3vior.R
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class SettingItemAdapter(
    list: List<SettingData>,
    clickListener: BaseInterfaceListener
) : BaseAdapter<SettingData>(list, clickListener) {
    override val layoutId: Int = R.layout.setting_item_layout
}