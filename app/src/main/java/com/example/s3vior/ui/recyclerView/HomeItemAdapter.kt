package com.example.s3vior.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.s3vior.R
import com.example.s3vior.databinding.RecycleHomeItemsBinding


class HomeItemAdapter(
    private var list: List<Case>,
    val clickListener: RecyclerViewInteractionListener
) : RecyclerView.Adapter<HomeItemAdapter.HomeViewHolder>() {

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = RecycleHomeItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_home_items, parent, false)
        return HomeViewHolder(view)
    }

    fun updateRecycler( newList: List<Case>){
        val diffResult=DiffUtil.calculateDiff(DiffUtilHomeLists(list,newList))
        list=newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            personImageView.setImageResource(currentItem.image)
            mafqoudDate.text=currentItem.data
            mafqoudLocation.text=currentItem.location
            mafqoudName.text=currentItem.name
            states.text=currentItem.state
        }

    }

    override fun getItemCount() = list.size

}


class DiffUtilHomeLists(private val oldListItem:List<Case>, private val newListItem:List<Case>):DiffUtil.Callback() {
    override fun getOldListSize()=oldListItem.size

    override fun getNewListSize()=newListItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListItem[oldItemPosition].id == newListItem[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListItem[oldItemPosition] == newListItem[newItemPosition]
    }
}
