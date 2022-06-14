package com.khalifa.github.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) = Glide.with(this.context).load(url).into(this)

