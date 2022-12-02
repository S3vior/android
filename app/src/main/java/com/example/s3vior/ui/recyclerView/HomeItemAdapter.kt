package com.example.s3vior.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s3vior.R
import com.example.s3vior.databinding.RecycleHomeItemsBinding


class HomeItemAdapter(
    private val list: List<Case>,
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
