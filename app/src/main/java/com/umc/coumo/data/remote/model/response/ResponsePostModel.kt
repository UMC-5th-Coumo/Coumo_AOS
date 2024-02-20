package com.umc.coumo.data.remote.model.response

data class ResponsePostModel(
    val storeId: Int,
    val title: String,
    val noticeType: String,
    val noticeContent: String,
    val storeName: String,
    val noticeImages: List<String>?,
    val createdAt: String
) {
}