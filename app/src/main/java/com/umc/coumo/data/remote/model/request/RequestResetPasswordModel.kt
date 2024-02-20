package com.umc.coumo.data.remote.model.request

data class RequestResetPasswordModel(
    val loginId: String,
    val newPassword: String
)
