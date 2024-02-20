package com.umc.coumo.domain.model

import android.net.Uri

data class CouponModel(
    val id: Int = 0,
    val name: String,
    val stampCount: Int,
    val couponColor: String? = "#FFDA26",
    val fontColor: String? = "#535043",
    val stampMax: Int = 10,
    val stampImage: Uri?,
) {
}