package com.example.s3vior.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.example.s3vior.R
import com.example.s3vior.domain.model.State
import com.example.s3vior.ui.recyclerView.baseAdapter.BaseAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    if (state is State.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?) {
    if (state is State.Error) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}



@BindingAdapter(value = ["app:hideLottieOnError"])
fun  <T> hideLottieOnError(lottieAnimationView: LottieAnimationView, state: State<T>?) {
    if (state is State.Error) {
        lottieAnimationView.visibility = View.GONE
    } else {
        lottieAnimationView.visibility = View.VISIBLE
    }
}
@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    if (state is State.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ShapeableImageView, url: String? = "https://res.cloudinary.com/khaledelabady11/image/upload/v1676773144/ehr7jby7solo1dsfh77q.png") {
    Glide.with(view).load(url).error(R.drawable.google).into(view)
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>?)?.setItems(items)
    } else {

        (view.adapter as BaseAdapter<T>?)?.setItems(emptyList())
    }

}
