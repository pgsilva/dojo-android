package com.example.dojo.view.commons

import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.example.dojo.R
import com.example.dojo.dao.fixtureCoverImage

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
        fallback(fixtureCoverImage())
        error(fixtureCoverImage())
        placeholder(R.drawable.gif_upload_animation)
    }
}