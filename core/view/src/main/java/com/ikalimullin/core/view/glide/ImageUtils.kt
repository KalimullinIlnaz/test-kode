package com.ikalimullin.core.view.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    // TODO добавить обработку ошибок
    fun ImageView.loadImage(
        requestOptions: RequestOptions,
        url: String
    ) {
        Glide
            .with(this)
            .load(url)
            .apply(requestOptions)
            .into(this)
    }
}
