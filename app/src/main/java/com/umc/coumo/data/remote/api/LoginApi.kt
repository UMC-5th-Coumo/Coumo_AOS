package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
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

}