package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import com.example.s3vior.R
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

class HomeItemAdapter(
    list: List<Person>,
    clickListener: BaseInterfaceListener
) :  BaseAdapter<Person>(list,clickListener){

    override val layoutId: Int = R.layout.animated_item_row


}



class DiffUtilHomeLists(private val oldListItem:List<Person>, private val newListItem:List<Person>):DiffUtil.Callback() {


    override fun getOldListSize()=oldListItem.size
    override fun getNewListSize()=newListItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListItem[oldItemPosition].id == newListItem[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListItem[oldItemPosition] == newListItem[newItemPosition]
    }
}
