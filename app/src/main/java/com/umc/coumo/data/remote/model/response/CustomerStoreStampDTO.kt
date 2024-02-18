package com.umc.coumo.data.remote.model.response

data class CustomerStoreStampDTO(
    val stampCurrent: Int,
    val stampMax: String,
    val updatedAt: String
)