package com.umc.coumo.domain.repository

import com.umc.coumo.domain.model.StoreInfoItemModel

interface CoumoRepository {

    suspend fun getPopularStoreList(longitude: Double, latitude: Double) : List<StoreInfoItemModel>?
}