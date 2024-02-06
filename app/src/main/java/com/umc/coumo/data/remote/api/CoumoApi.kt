package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/** 각 기능 별 API */
interface CoumoApi {

    @GET("/api/customer/store")
    suspend fun getPopularStoreList(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<List<ResponsePopularStoreModel>>

    @POST("/customer/join")
    suspend fun postJoin(
        @Body params: RequestJoinModel
    ): Response<RequestJoinModel>

    @POST("/customer/login")
    suspend fun postLogin(
        @Body params: RequestLoginModel
    ): Response<ResponseLoginModel>

}