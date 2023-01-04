package com.example.s3vior.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.s3vior.R
import com.example.s3vior.databinding.AnimatedItemRowBinding


class HomeItemAdapter(
    private var list: List<Person>,
    private val clickListener: RecyclerViewInteractionListener
) : RecyclerView.Adapter<HomeItemAdapter.HomeViewHolder>() {

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AnimatedItemRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.animated_item_row, parent, false)
        return HomeViewHolder(view)
    }

    fun updateRecycler( newList: List<Person>){
        val diffResult=DiffUtil.calculateDiff(DiffUtilHomeLists(list,newList))
        list=newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            Glide.with(holder.binding.root.context)
                .load(currentItem.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(personImageView)
//            personImageView.setImageResource(currentItem.image)
            mafqoudDate.text=currentItem.message
//            mafqoudDescription.text=currentItem.description
            mafqoudName.text=currentItem.name
//            age.text=currentItem.age.toString()
        }
        val animation = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.slide_in)
        holder.itemView.startAnimation(animation)
        holder.itemView.setOnClickListener {clickListener.onClickItem(it)  }

    }

    override fun getItemCount() = list.size

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
