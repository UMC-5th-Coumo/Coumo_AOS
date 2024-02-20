package com.umc.coumo.data.remote.model.response

data class ResponseVerifyIdCodeModel(
    val loginId: String,
    val verificationCode: String
)
