package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.type.CategoryType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/** 각 기능 별 API */
interface CoumoApi {

    @GET("/api/customer/store")
    suspend fun getPopularStoreList(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<ResponseModel<List<ResponsePopularStoreModel>>>

    @GET("/api/customer/store")
    suspend fun getNearStoreList(
        @Query("category") category: String? = CategoryType.DEFAULT.api,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("page") page: Int?
    ): Response<ResponseModel<List<ResponseNearStoreModel>>>

    @GET("/api/customer/store/{storeId}/detail")
    suspend fun getStoreData(
        @Path("storeId") storeId: Int
    ): Response<ResponseModel<ResponseStoreDataModel>>
}