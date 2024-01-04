package com.example.dojo.ui

import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.example.dojo.R

fun ImageView.load(url: String? = null) {
    val loader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    load(url, loader) {
        fallback(R.drawable.ic_default_avatar)
        error(R.drawable.ic_default_avatar)
        placeholder(R.drawable.gif_upload_animation)
    }
}