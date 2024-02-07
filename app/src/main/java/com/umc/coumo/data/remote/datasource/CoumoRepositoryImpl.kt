package com.umc.coumo.data.remote.datasource

import android.net.Uri
import com.umc.coumo.data.remote.api.CoumoApi
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CategoryType
import javax.inject.Inject

class CoumoRepositoryImpl @Inject constructor(
    //API Injection
    private val coumoApi: CoumoApi
): CoumoRepository {
    override suspend fun getPopularStoreList(
        longitude: Double,
        latitude: Double
    ): List<StoreInfoItemModel>? {
        val data = coumoApi.getPopularStoreList(longitude = longitude,latitude = latitude)
        return mapToStoreInfoItemModelList(data.body()?.result)
    }

    override suspend fun getNearStoreList(
        category: CategoryType?,
        longitude: Double,
        latitude: Double,
        page: Int?
    ): List<StoreCouponCountModel>? {
        val data = coumoApi.getNearStoreList(
            category?.api, longitude, latitude, page)
        return mapToStoreCouponCountModelList(data.body()?.result)
    }

    private fun mapToStoreCouponCountModelList(responseList: List<ResponseNearStoreModel>?): List<StoreCouponCountModel>? {
        return responseList?.map { response ->
            StoreCouponCountModel(
                id = response.storeId,
                image = Uri.parse(response.storeImage),
                name = response.name,
                coupon = response.couponCnt
            )
        }
    }

    private fun mapToStoreInfoItemModelList(responseList: List<ResponsePopularStoreModel>?): List<StoreInfoItemModel>? {
        return responseList?.map { response ->
            StoreInfoItemModel(
                id = response.storeId,
                image = Uri.parse(response.storeImage),
                name = response.name,
                address = response.location,
                description = response.description,
            )
        }
    }

}