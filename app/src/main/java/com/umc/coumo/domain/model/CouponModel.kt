package com.umc.coumo.domain.model

import android.net.Uri

data class CouponModel(
    val name: String,
    val stampCount: Int,
    val color: String = "#FFDA26",
    val fontColor: String = "#535043",
    val stampMax: Int = 10,
    val stampImage: Uri?,
) {
}