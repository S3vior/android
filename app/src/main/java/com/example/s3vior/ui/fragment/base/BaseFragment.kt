package com.example.s3vior.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VB : ViewDataBinding>(
        private val bindingInflater: (inflater: LayoutInflater) -> VB,
        @LayoutRes private val layoutResId : Int
    ) : Fragment()  {


        private var _binding: VB? = null
        val binding: VB
            get() = _binding as VB



        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)



            if (_binding == null) {
                throw IllegalArgumentException("binding cannot be null")
            }

            initViewModel()
            recyclerAdapter()
            return _binding!!.root
        }

        abstract fun initViewModel()
        abstract fun recyclerAdapter()

    }

