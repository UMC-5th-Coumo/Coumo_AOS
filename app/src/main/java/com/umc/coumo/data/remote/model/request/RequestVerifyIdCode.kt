package com.umc.coumo.data.remote.model.request

data class RequestVerifyIdCode(
    val phone: String = "01012345678",
    val verificationCode: String = "0"
)
