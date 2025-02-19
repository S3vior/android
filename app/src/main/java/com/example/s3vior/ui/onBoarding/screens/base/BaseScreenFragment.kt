package com.example.s3vior.ui.onBoarding.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseInterfaceListener

abstract class BaseScreenFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB

) : Fragment() ,BaseInterfaceListener{

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }

        initButton()
        initSpinner()
        return binding.root
    }

    abstract fun initButton()

    abstract fun initSpinner()

}