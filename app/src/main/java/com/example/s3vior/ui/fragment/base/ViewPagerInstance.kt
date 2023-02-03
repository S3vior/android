package com.example.s3vior.ui.fragment.base

import android.app.Activity
import androidx.viewpager2.widget.ViewPager2
import com.example.s3vior.R

object ViewPagerInstance {
    fun callFunction(currentItem:Int ,view: Activity){
        val viewPager  = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem =currentItem
    }
}