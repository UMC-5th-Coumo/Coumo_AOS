package com.umc.coumo.data.remote.model.response

data class ResponseStoreDataModel(
    val name: String,
    val description: String,
    val location: String,
    val longitude: String,
    val latitude: String,
    val telephone: String,
    val time: List<ResponseTimeModel>,
    val images: List<String>,
    val coupon: ResponseCouponModel,
    val menus: List<ResponseMenuModel>?,
)

