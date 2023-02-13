package com.example.s3vior.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAllPersonInfoBinding
import com.example.s3vior.ui.fragment.base.BaseFragment


class AllPersonInfo : BaseFragment<FragmentAllPersonInfoBinding>(
    FragmentAllPersonInfoBinding::inflate
) {
    override fun callFunctions() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(
            android.R
                .transition.slide_left
        )
    }

}