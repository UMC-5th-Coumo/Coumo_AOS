package com.umc.coumo.data.remote.model.response

data class StoreCouponDTO(
    val couponColor: String,
    val fontColor: String,
    val stampImage: String,
    val storeId: Int,
    val storeName: String
)