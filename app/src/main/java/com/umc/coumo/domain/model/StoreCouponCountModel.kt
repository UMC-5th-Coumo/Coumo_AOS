package com.umc.coumo.domain.model

import android.net.Uri

data class StoreCouponCountModel(
    val id : Int,
    val image: Uri? = null,
    val name: String,
    val coupon: Int,
)
