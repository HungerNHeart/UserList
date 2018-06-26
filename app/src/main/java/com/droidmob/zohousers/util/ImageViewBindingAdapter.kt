package com.droidmob.zohousers.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Guru on 25-03-2018.
 */
class ImageViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImage(imageView: ImageView, url: String?) {
            try {
                Glide.with(imageView.context)
                        .load(url)
                        .apply(RequestOptions().transform(CircleCrop()))
                        .transition(withCrossFade())
                        .into(imageView)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("backgroundImageUrl")
        fun bindBackgroundImageUrl(imageView: ImageView, url: String?) {
            try {
                Glide.with(imageView.context)
                        .load(url)
                        .transition(withCrossFade())
                        .into(imageView)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }
}