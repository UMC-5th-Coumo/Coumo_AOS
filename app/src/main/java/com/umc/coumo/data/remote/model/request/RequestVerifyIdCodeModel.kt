package com.umc.coumo.data.remote.model.request

data class RequestVerifyIdCodeModel(
    val phone: String = "01012345678",
    val verificationCode: String = "0"
)
