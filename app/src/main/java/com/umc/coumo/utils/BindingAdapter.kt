package com.umc.coumo.utils

import android.content.res.Resources
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.umc.coumo.R

@BindingAdapter("app:selectedNaviMargin")
fun setSelectedMargin(view: View, isSelected: Boolean) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    val density = view.resources.displayMetrics.density
    val marginInDp = if (isSelected) 30 else 22
    val marginInPx = (marginInDp * density).toInt()

    if (isSelected) {
        // 선택된 경우 마진 변경
        layoutParams.setMargins(0, 0, 0, marginInPx)
    } else {
        // 선택되지 않은 경우 마진 변경
        layoutParams.setMargins(0, 0, 0, marginInPx)
    }
    view.layoutParams = layoutParams
}

@BindingAdapter("app:selectedNaviMargin2")
fun setSelectedMargin2(view: View, isSelected: Boolean) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    val density = view.resources.displayMetrics.density
    val marginInDp = if (isSelected) 14 else 6
    val marginInPx = (marginInDp * density).toInt()

    if (isSelected) {
        // 선택된 경우 마진 변경
        layoutParams.setMargins(0, 0, 0, marginInPx)
    } else {
        // 선택되지 않은 경우 마진 변경
        layoutParams.setMargins(0, 0, 0, marginInPx)
    }
    view.layoutParams = layoutParams
}

@BindingAdapter("selected")
fun setSelect(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}

//사진 삽입
@BindingAdapter("imageUri")
fun setImageUri(imageView: ImageView, imageUri: Uri?) {
    if (imageUri != null) {
        Glide.with(imageView)
            .load(imageUri)
            .apply(RequestOptions().transform(RoundedCorners(((4f) * Resources.getSystem().displayMetrics.density).toInt())))
            .into(imageView)
    } else {
        imageView.setImageResource(R.drawable.default_image);
    }
}