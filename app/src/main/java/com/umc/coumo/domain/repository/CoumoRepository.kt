package com.umc.coumo.domain.repository

import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.type.CategoryType

interface CoumoRepository {

    suspend fun getPopularStoreList(longitude: Double, latitude: Double) : List<StoreInfoItemModel>?
    suspend fun getNearStoreList(
        category: CategoryType? = CategoryType.DEFAULT,
        longitude: Double,
        latitude: Double,
        page: Int? = 0
    ): List<StoreCouponCountModel>?
}