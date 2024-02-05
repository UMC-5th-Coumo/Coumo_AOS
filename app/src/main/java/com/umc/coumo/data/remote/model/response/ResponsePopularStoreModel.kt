package com.umc.coumo.data.remote.model.response

data class ResponsePopularStoreModel(
    val storeId: Int,
    val name: String,
    val location: String,
    val description: String,
    val storeImage: String
)