package dev.epool.hellokmp

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher_round)
        transformations(CircleCropTransformation())
    }
}