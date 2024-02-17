package com.umc.coumo.data.remote.model.request

data class RequestOwnerQRModel(
    val customerId: Int = 0,
    val storeId: Int = 1,
    val ownerId: Int = 1,
    val stampCnt: Int = 1,
) {
}