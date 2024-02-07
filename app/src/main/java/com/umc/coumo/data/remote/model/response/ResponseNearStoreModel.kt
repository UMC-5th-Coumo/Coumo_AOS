package com.umc.coumo.data.remote.model.response

data class ResponseNearStoreModel(
    val storeId: Int,
    val name: String,
    val location: String,
    val couponCnt: Int,
    val storeImage: String
)