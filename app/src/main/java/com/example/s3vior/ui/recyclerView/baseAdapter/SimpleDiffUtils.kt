package com.example.s3vior.ui.recyclerView.baseAdapter

import androidx.recyclerview.widget.DiffUtil

class SimpleDiffUtils<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>,
    private val checkIfSameItem: (oldItem:T,newItem:T) -> Boolean
) :DiffUtil.Callback(){
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkIfSameItem(oldItems[oldItemPosition],newItems[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true


}