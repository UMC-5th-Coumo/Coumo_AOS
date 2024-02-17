package com.umc.coumo.data.remote.model.response

data class ResponseLoginAsOwnerModel(
    val ownerId: Int = 1,
    val storeId: Int = 1,
    val token: String = "",
    val createAt: String = "",
    val write: Boolean = false
)
