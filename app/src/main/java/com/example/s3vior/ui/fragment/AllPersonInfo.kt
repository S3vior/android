package com.example.s3vior.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
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
