package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.notification

import com.example.s3vior.R
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class NotificationAdapter(
    list: List<NotificationContent>,
    clickListener: BaseInterfaceListener
) : BaseAdapter<NotificationContent>(list, clickListener) {
    override val layoutId: Int
        get() = R.layout.notification_item_layout
}