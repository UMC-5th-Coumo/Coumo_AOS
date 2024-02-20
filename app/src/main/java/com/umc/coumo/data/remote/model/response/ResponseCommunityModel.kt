package com.umc.coumo.data.remote.model.response

data class ResponseCommunityModel(
    val title: String,
    val noticeType: String,
    val noticeContent: String,
    val storeName: String,
    val createAt: String,
    val noticeImage: List<String?>
)
