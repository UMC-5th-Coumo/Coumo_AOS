package com.umc.coumo.domain.model

import android.net.Uri

data class StoreInfoModel(
    val name: String,
    val description: String,
    val location: String,
    val longitude: Double,
    val latitude: Double,
    val image: List<Uri>?,
    val coupon: CouponModel,
    val menuList: List<MenuModel>,
) {
}