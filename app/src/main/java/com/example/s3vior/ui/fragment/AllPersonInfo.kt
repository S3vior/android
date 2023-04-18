package com.example.s3vior.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentAllPersonInfoBinding
import com.example.s3vior.networking.API
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.viewModel.SharedViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


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
