package com.umc.coumo.data.remote.datasource

import android.net.Uri
import com.umc.coumo.App
import com.umc.coumo.data.remote.api.CoumoApi
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.utils.Constants.CUSTOMER_ID
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

    override suspend fun getStoreData(storeId: Int): StoreInfoModel? {
        val data = coumoApi.getStoreData(App.prefs.getInt(CUSTOMER_ID,1),storeId)
        return mapToStoreInfoModel(data.body()?.result)
    }

    private fun mapToStoreInfoModel(response: ResponseStoreDataModel?): StoreInfoModel? {
        return if (response != null) {
            StoreInfoModel(
                name = response.name,
                description = response.description,
                location = response.location,
                longitude = response.longitude.toDouble(),
                latitude = response.latitude.toDouble(),
                image = response.images.map {
                    Uri.parse(it)
                },
                coupon = CouponModel(
                    name = response.coupon.title,
                    stampCount = response.coupon.cnt,
                    color = response.coupon.color,
                    stampMax = 10,
                    stampImage = Uri.parse(response.coupon.stampType)
                ),
                menuList = response.menus?.map {
                    MenuModel(
                        name = it.name,
                        description = it.description,
                        image = Uri.parse(it.image),
                        isNew = it.isNew
                    )
                }
            )
        } else null

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