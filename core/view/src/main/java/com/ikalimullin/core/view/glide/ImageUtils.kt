package com.ikalimullin.core.view.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    fun ImageView.loadImage(
        url: String,
        requestOptions: RequestOptions = RequestOptions(),
    ) {
        Glide
            .with(this)
            .load(url)
            .apply(requestOptions)
            .into(this)
    }
}
