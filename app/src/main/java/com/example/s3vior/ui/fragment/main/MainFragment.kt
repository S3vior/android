package com.example.s3vior.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMainBinding
import com.example.s3vior.ui.fragment.bottomFragment.AnnouncementFragment
import com.example.s3vior.ui.fragment.bottomFragment.HomeFragment
import com.example.s3vior.ui.fragment.bottomFragment.MapsFragment
import com.example.s3vior.ui.fragment.bottomFragment.MoreFragment

class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private val announcementFragment=AnnouncementFragment()
    private val homeFragment=HomeFragment()
    private val mapsFragment=MapsFragment()
    private val moreFragment= MoreFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)

        initSubView()
        addNavigationListener()
        return binding.root
    }

    private fun addFragment(fragment: Fragment) {
        val transactions = requireActivity().supportFragmentManager.beginTransaction()
        transactions.add(R.id.fragment_container, fragment)
        transactions.commit()
    }
    private fun replaceFragment(fragment: Fragment) {
        val transactions = requireActivity().supportFragmentManager.beginTransaction()
        transactions.replace(R.id.fragment_container, fragment)
        transactions.commit()
    }

    private fun initSubView() {
        addFragment(homeFragment)
    }

    private fun addNavigationListener() {
        binding.bottomNavigationView.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.report ->{
                    replaceFragment(announcementFragment)
                    true
                }
                R.id.more -> {
                    replaceFragment(moreFragment)
                    true
                }
                R.id.map ->{
                    replaceFragment(mapsFragment)
                    true

                }
                else -> false
            }

        }
    }
}