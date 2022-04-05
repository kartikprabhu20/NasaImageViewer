package com.challenge.nasaimageviewer.bindings

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@BindingAdapter(value = ["imageUrl","attachedProgressBar"], requireAll = true)
fun loadImage(view: ImageView, url: String?, progressbar:ProgressBar) {
    progressbar.visibility = View.VISIBLE
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressbar.visibility = View.GONE
                    return false
                }
            }).into(view)
    }
}