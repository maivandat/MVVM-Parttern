package com.sun.mvvm_pattern.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sun.mvvm_pattern.R

object BindingUtils {
    @JvmStatic
    @BindingAdapter("image")
    fun image(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .apply(RequestOptions.errorOf(R.drawable.ic_launcher_background))
            .into(view)
    }
}