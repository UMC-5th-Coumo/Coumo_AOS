package com.umc.coumo.domain.model

import android.net.Uri

data class CouponModel(
    val name: String,
    val stampCount: Int,
    val color: String,
    val stampMax: Int,
    val stampImage: Uri?,
) {
}