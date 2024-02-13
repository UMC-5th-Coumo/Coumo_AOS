package com.umc.coumo.domain.repository

import android.net.Uri
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.type.CategoryType

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

    suspend fun postStampCustomer(
        storeId: Int
    ): String?

    suspend fun postPaymentCustomer(
        storeId: Int
    ): Uri?

}