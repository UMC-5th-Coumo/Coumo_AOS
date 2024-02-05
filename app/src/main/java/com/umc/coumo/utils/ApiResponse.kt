package com.umc.coumo.utils

import com.umc.coumo.domain.type.Status


data class ApiResponse<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(Status.SUCCESS, data, null)
        fun <T> error(throwable: Throwable): ApiResponse<T> = ApiResponse(Status.ERROR, null, throwable)
        fun <T> loading(data: T? = null): ApiResponse<T> = ApiResponse(Status.LOADING, data, null)
    }
}