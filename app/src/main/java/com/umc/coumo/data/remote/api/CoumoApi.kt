package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/** 각 기능 별 API */
interface CoumoApi {

    @GET("/api/customer/store")
    suspend fun getPopularStoreList(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<List<ResponsePopularStoreModel>>

}