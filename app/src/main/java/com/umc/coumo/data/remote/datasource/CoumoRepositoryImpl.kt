package com.umc.coumo.data.remote.datasource

import android.net.Uri
import com.umc.coumo.data.remote.api.CoumoApi
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.repository.CoumoRepository
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
        return mapToStoreInfoItemModelList(data.body())
    }

    override suspend fun postJoin() {
        coumoApi.postLogin(RequestLoginModel())
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