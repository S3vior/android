package com.example.s3vior.ui.fragment.addPersonDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentMorePersonDetailBinding


class MorePersonDetailFragment : Fragment() {

    private lateinit var binding:FragmentMorePersonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_more_person_detail, container, false)
        return binding.root
    }


}