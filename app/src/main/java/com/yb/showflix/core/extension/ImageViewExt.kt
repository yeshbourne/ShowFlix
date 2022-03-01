package com.yb.showflix.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yb.showflix.R

fun ImageView.load(uri: String){
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}