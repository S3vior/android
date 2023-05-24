package com.example.s3vior.ui.fragment.scrapedPersons

import com.example.s3vior.R
import com.example.s3vior.data.source.remote.responseModels.ScrapedPersonsResponseItem
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class ScrapedItemAdapter(
    list: List<ScrapedPersonsResponseItem>,
    clickListener: BaseInterfaceListener
) : BaseAdapter<ScrapedPersonsResponseItem>(list, clickListener) {

    override val layoutId: Int = R.layout.scraped_recyclerview_item
}

