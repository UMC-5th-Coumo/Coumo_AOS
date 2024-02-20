package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestCheckDupIdModel
import com.umc.coumo.data.remote.model.request.RequestFindIdModel
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestJoinRequestVerificationModel
import com.umc.coumo.data.remote.model.request.RequestJoinVerifyCodeModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.request.RequestResetPasswordModel
import com.umc.coumo.data.remote.model.request.RequestVerifyIdCodeModel
import com.umc.coumo.data.remote.model.response.ResponseCheckDupIdModel
import com.umc.coumo.data.remote.model.response.ResponseJoinModel
import com.umc.coumo.data.remote.model.response.ResponseJoinVerifyCodeModel
import com.umc.coumo.data.remote.model.response.ResponseLoginAsOwnerModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel
import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.data.remote.model.response.ResponseVerifyIdCodeModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/** 각 기능 별 API */
interface LoginApi {

    @POST("/customer/join")
    suspend fun postJoin(
        @Body params: RequestJoinModel
    ): Response<ResponseModel<ResponseJoinModel>>

    @POST("/customer/join/send-verification-code")
    suspend fun postJoinRequestVerification(
        @Body params: RequestJoinRequestVerificationModel
    ): Response<ResponseModel<String>>

    @POST("/customer/join/verify-code")
    suspend fun postJoinVerifyCode(
        @Body params: RequestJoinVerifyCodeModel
    ): Response<ResponseModel<ResponseJoinVerifyCodeModel>>

    @POST("/customer/login")
    suspend fun postLogin(
        @Body params: RequestLoginModel
    ): Response<ResponseModel<ResponseLoginModel>>

    @POST("/customer/join/check-login-id")
    suspend fun postCheckDupId(
        @Body params: RequestCheckDupIdModel
    ): Response<ResponseModel<ResponseCheckDupIdModel>>

    @POST("/customer/find-id")
    suspend fun postFindIdRequestCode(
        @Body params: RequestFindIdModel
    ): Response<ResponseModel<String>>

    @POST("/customer/verify-code")
    suspend fun postVerifyIdCode(
        @Body params: RequestVerifyIdCodeModel
    ): Response<ResponseModel<ResponseVerifyIdCodeModel>>

    @POST("/owner/login")
    suspend fun postLoginAsOwner(
        @Body params: RequestLoginModel
    ): Response<ResponseModel<ResponseLoginAsOwnerModel>>

    @POST("/customer/reset-password/set-pw")
    suspend fun postResetPassword(
        @Body params: RequestResetPasswordModel
    ): Response<ResponseModel<String>>

}