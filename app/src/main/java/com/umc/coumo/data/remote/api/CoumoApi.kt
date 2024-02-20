package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestOwnerQRModel
import com.umc.coumo.data.remote.model.response.ResponseCommunityModel
import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.data.remote.model.response.ResponseMyPageModel
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.type.CategoryType
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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

    //마이 페이지
    @GET("/customer/mypage/{customerId}/profile")
    suspend fun getMyPageData(
        @Path("customerId") customerId: Int,
    ): Response<ResponseModel<ResponseMyPageModel>>

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

    @POST("/api/qr/owner/stamp")
    suspend fun postOwnerStamp(
        @Body body: RequestOwnerQRModel
    ): Response<Any>

    @POST("/api/qr/owner/payment")
    suspend fun postOwnerPayment(
        @Body body: RequestOwnerQRModel
    ): Response<Any>

    @GET("/api/notice/around/list")
    suspend fun getCommunityAll(
        @Query("type") type: String,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("pageId") pageId: Int
    ): Response<ResponseModel<List<ResponseCommunityModel>>>
}