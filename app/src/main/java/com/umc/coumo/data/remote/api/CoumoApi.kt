package com.umc.coumo.data.remote.api

import com.umc.coumo.data.remote.model.request.RequestOwnerQRModel
import com.umc.coumo.data.remote.model.response.ResponseCouponModelX
import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.data.remote.model.response.ResponseMyPageModel
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePostModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.type.CommunityTabType
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

    @GET("/api/notice/around/list")
    suspend fun getCommunityList(
        @Query("type") category: String? = CommunityTabType.ALL.api,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("pageId") pageId: Int,
    ): Response<ResponseModel<List<ResponsePostModel>>>

    //내 쿠폰 보기 (필터)
    @GET("/api/coupon/{customerId}/list")
    suspend fun getCouponList(
        @Path("customerId") customerId: Int,
        @Query("filter") filter: String
    ): Response<ResponseModel<ResponseCouponModelX>>

    @POST("/api/qr/owner/stamp")
    suspend fun postOwnerStamp(
        @Body body: RequestOwnerQRModel
    ): Response<Any>

    @POST("/api/qr/owner/payment")
    suspend fun postOwnerPayment(
        @Body body: RequestOwnerQRModel
    ): Response<Any>

}