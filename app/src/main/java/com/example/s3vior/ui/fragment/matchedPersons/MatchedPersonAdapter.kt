package com.example.s3vior.ui.fragment.matchedPersons

import com.example.s3vior.R
import com.example.s3vior.ui.fragment.matchedPersons.model.MatchedPersonsResponseModelItem
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class MatchedPersonAdapter(
    list: List<MatchedPersonsResponseModelItem>,
    clickListener: BaseInterfaceListener
) : BaseAdapter<MatchedPersonsResponseModelItem>(list, clickListener) {

    override val layoutId: Int = R.layout.matched_persons_card
}