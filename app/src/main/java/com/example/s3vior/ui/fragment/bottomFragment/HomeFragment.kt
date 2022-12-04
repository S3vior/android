package com.example.s3vior.ui.fragment.bottomFragment

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.personDetails.MorePersonDetailFragment
import com.example.s3vior.ui.recyclerView.Case
import com.example.s3vior.ui.recyclerView.HomeItemAdapter
import com.example.s3vior.ui.recyclerView.RecyclerViewInteractionListener
import okhttp3.internal.Internal

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {
    override fun initViewModel() {
    }

    override fun recyclerAdapter() {
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val list = listOf<Case>(
            Case(1,R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found")
        ,Case(1,R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),Case(1,R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),Case(1,R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found")
        )
        binding.recyclerView.adapter = HomeItemAdapter(list, this)
    }

    override fun onClickItem(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_morePersonDetailFragment)
    }
}