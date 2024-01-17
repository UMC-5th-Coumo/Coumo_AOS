package com.umc.coumo.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
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

@BindingAdapter("app:selected")
fun setSelect(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}
