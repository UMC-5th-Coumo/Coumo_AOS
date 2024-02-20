package com.umc.coumo.domain.repository

import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MyPageModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.type.CouponAlignType

interface CoumoRepository {

    suspend fun getPopularStoreList(longitude: Double, latitude: Double) : List<StoreInfoItemModel>?
    suspend fun getNearStoreList(
        category: CategoryType? = CategoryType.DEFAULT,
        longitude: Double,
        latitude: Double,
        page: Int? = 0
    ): List<StoreCouponCountModel>?

    suspend fun getStoreData(
        storeId: Int
    ): StoreInfoModel?

    suspend fun getMyPage(): MyPageModel?

    suspend fun getCouponList(
        filter: CouponAlignType,
    ): List<CouponModel>?

    suspend fun postOwnerStamp(
        storeId: Int,
        customerId: Int,
        stampCnt: Int,
    ): Boolean

    suspend fun postOwnerPayment(
        storeId: Int,
        customerId: Int,
        stampCnt: Int,
    ): Boolean
}