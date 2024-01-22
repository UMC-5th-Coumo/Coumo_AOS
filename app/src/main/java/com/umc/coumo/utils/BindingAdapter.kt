package com.umc.coumo.utils

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
            .fitCenter().centerCrop()
            .into(imageView)
    } else {
        imageView.setImageResource(R.drawable.default_image)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
    }
}

@BindingAdapter("highlightText")
fun TextView.setHighlightedText(fullText: String?) {
    fullText?.let {
        val spannableString = SpannableString(fullText)

        val targetText = listOf("많이", "최근") // 원하는 단어들
        for (text in targetText) {
            val startIndex = fullText.indexOf(text)
            if (startIndex != -1) {
                val endIndex = startIndex + text.length

                val color = ContextCompat.getColor(context, R.color.font_main)
                val colorSpan = ForegroundColorSpan(color)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        text = spannableString
    }
}