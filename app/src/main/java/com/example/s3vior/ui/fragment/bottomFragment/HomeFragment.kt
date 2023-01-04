package com.example.s3vior.ui.fragment.bottomFragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.recyclerView.HomeItemAdapter
import com.example.s3vior.ui.recyclerView.Person
import com.example.s3vior.ui.recyclerView.RecyclerViewInteractionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {
    override fun initViewModel() {
    }

    override fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
//        val list = listOf(
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//            Person(1, R.drawable.testimage, "Eslam", "Cairo,Egypt", "3-12-2022", "Found"),
//        )
//        binding.recyclerView.adapter = HomeItemAdapter(list, this)
    }

    override fun onClickItem(view: View) {
    }
}