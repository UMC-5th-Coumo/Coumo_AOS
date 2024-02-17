package com.umc.coumo.data.remote.model.response

data class ResponseCouponModel(
    val title: String,
    val cnt: Int,
    val stampType: String,
    val stampMax: Int,
    val couponColor: String,
    val fontColor: String,
)

data class ResponseCoupon2Model(
    val couponColor: String,
    val fontColor: String,
    val storeName: String,
    val stampImage: String
)

data class ResponseStampModel(
    val stampCurrent: Int,
    val stampMax: Int,
)
