package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestCheckDupIdModel
import com.umc.coumo.data.remote.model.request.RequestFindIdModel
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.request.RequestVerifyIdCode
import com.umc.coumo.data.remote.model.response.ResponseCheckDupIdModel
import com.umc.coumo.data.remote.model.response.ResponseJoinModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel
import com.umc.coumo.data.remote.model.response.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/** 각 기능 별 API */
interface LoginApi {

    @POST("/customer/join")
    suspend fun postJoin(
        @Body params: RequestJoinModel
    ): Response<ResponseModel<ResponseJoinModel>>

    @POST("/customer/login")
    suspend fun postLogin(
        @Body params: RequestLoginModel
    ): Response<ResponseModel<ResponseLoginModel>>

    @POST("/customer/join/check-login-id")
    suspend fun postCheckDupId(
        @Body params: RequestCheckDupIdModel
    ): Response<ResponseModel<ResponseCheckDupIdModel>>

    @POST("/customer/find-id")
    suspend fun postFindId(
        @Body params: RequestFindIdModel
    ): Response<ResponseModel<String>>

    @POST("/customer/verify-code")
    suspend fun postVerifyIdCode(
        @Body params: RequestVerifyIdCode
    ): Response<ResponseModel<String>>

}