package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.type.CategoryType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/** 각 기능 별 API */
interface CoumoApi {

    @GET("/api/customer/store")
    suspend fun getPopularStoreList(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<ResponseModel<List<ResponsePopularStoreModel>>>

    @GET("/api/customer/{customerId}/store")
    suspend fun getNearStoreList(
        @Path("customerId") customerId: Int,
        @Query("category") category: String? = CategoryType.DEFAULT.api,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("page") page: Int?
    ): Response<ResponseModel<List<ResponseNearStoreModel>>>

    @GET("/api/customer/{customerId}/store/{storeId}/detail")
    suspend fun getStoreData(
        @Path("customerId") customerId: Int,
        @Path("storeId") storeId: Int
    ): Response<ResponseModel<ResponseStoreDataModel>>

    //QR 생성 (적립)
    @Headers("Content-Type: image/png")
    @GET("/api/qr/customer/stamp/{customerId}/{storeId}")
    suspend fun postCustomerStamp(
        @Path("customerId") customerId: Int,
        @Path("storeId") storeId: Int,
    ): String

    //QR 생성 (사용)
    @POST("/api/qr/customer/payment/{customerId}/{storeId}")
    suspend fun postCustomerPayment(
        @Path("customerId") customerId: Int,
        @Path("storeId") storeId: Int
    ): Response<Any>

    @GET("/api/notice/around/list/{pageId}")
    suspend fun getCommunityList(
        @Path("pageId") pageId: Int,
        @Query("type") category: String? = CategoryType.DEFAULT.api,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
    )

    //내 쿠폰 보기 (필터)
    @GET("/api/coupon/{customerId}/list")
    suspend fun getCouponList(
        @Path("customerId") customerId: Int,
        @Query("filter") filter: String
    ): Response<ResponseModel<Any>>

    //가게의 내 쿠폰 보기
    @GET("/api/coupon/{storeId}/{customerId}")
    suspend fun getCouponStore(
        @Path("storeId") storeId: Int,
        @Path("customerId") customerId: Int,
    ): Response<ResponseModel<Any>>

}