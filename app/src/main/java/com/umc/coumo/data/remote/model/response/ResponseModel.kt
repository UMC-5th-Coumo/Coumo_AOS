package com.umc.coumo.data.remote.model.response

data class ResponseModel<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: T
)
