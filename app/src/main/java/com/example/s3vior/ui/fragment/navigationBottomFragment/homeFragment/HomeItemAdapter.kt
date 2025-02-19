package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import com.example.s3vior.R
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class HomeItemAdapter(
    list: List<MafqoudModel>,
    clickListener: BaseInterfaceListener
) :  BaseAdapter<MafqoudModel>(list,clickListener){

    override val layoutId: Int = R.layout.home_recyclerview_item
}



//class DiffUtilHomeLists(private val oldListItem:List<Person>, private val newListItem:List<Person>):DiffUtil.Callback() {
//
//
//    override fun getOldListSize()=oldListItem.size
//    override fun getNewListSize()=newListItem.size
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldListItem[oldItemPosition].description == newListItem[newItemPosition].description
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldListItem[oldItemPosition] == newListItem[newItemPosition]
//    }
//}
