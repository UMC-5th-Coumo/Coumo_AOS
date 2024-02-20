package com.umc.coumo.data.remote.datasource

import android.net.Uri
import android.util.Log
import com.umc.coumo.App
import com.umc.coumo.data.remote.api.CoumoApi
import com.umc.coumo.data.remote.model.request.RequestOwnerQRModel
import com.umc.coumo.data.remote.model.response.ResponseCommunityModel
import com.umc.coumo.data.remote.model.response.ResponseMyPageModel
import com.umc.coumo.data.remote.model.response.ResponseNearStoreModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.data.remote.model.response.ResponseStoreDataModel
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.model.MyPageModel
import com.umc.coumo.domain.model.PostItemModel
import com.umc.coumo.domain.model.PostModel
import com.umc.coumo.domain.model.RunTimeModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.type.CouponAlignType
import com.umc.coumo.utils.Constants.CUSTOMER_ID
import com.umc.coumo.utils.Constants.OWNER_ID
import java.time.LocalDate
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
            App.prefs.getInt(CUSTOMER_ID, 0),
            category?.api, longitude, latitude, page)
        return mapToStoreCouponCountModelList(data.body()?.result)
    }

    override suspend fun getStoreData(storeId: Int): StoreInfoModel? {
        val data = coumoApi.getStoreData(App.prefs.getInt(CUSTOMER_ID,0),storeId)
        return mapToStoreInfoModel(data.body()?.result)
    }

    override suspend fun getMyPage(): MyPageModel? {
        val data = coumoApi.getMyPageData(App.prefs.getInt(CUSTOMER_ID,0))
        return mapToMyPageModel(data.body()?.result)
    }

    override suspend fun getCouponList(filter: CouponAlignType): List<CouponModel> {
        val data = coumoApi.getCouponList(App.prefs.getInt(CUSTOMER_ID,0),filter.api)
        Log.d("TEST http list", "${data.body()}")
        return emptyList()
    }

    override suspend fun getCouponStore(storeId: Int): CouponModel? {
        val data = coumoApi.getCouponStore(App.prefs.getInt(CUSTOMER_ID,0),storeId)
        Log.d("TEST http store", "${data.body()}")
        return CouponModel("",0, stampImage = null)
    }

    override suspend fun postOwnerStamp(
        storeId: Int,
        customerId: Int,
        stampCnt: Int
    ): Boolean {
        val data = coumoApi.postOwnerStamp(RequestOwnerQRModel(
            storeId = storeId,
            customerId = customerId,
            ownerId = App.prefs.getInt(OWNER_ID,35),
            stampCnt = stampCnt
        ))
        return data.isSuccessful
    }

    override suspend fun postOwnerPayment(
        storeId: Int,
        customerId: Int,
        stampCnt: Int
    ): Boolean {
        val data = coumoApi.postOwnerPayment(RequestOwnerQRModel(
            storeId = storeId,
            customerId = customerId,
            ownerId = App.prefs.getInt(OWNER_ID,35),
            stampCnt = stampCnt
        ))
        return data.isSuccessful
    }

    override suspend fun getCommunityAll(
        longitude: Double,
        latitude: Double,
        pageId: Int
    ): List<PostModel>? {
        val data = coumoApi.getCommunityAll(
            longitude = longitude,
            latitude = latitude,
            pageId = pageId,
            type = "(null)")
        //return mapToPostModelList(data.body()?.result)
        return listOf<PostModel>(
            PostModel("0", "야호~", "2322", "야호~", null),
            PostModel("1", "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", "2323", "곽철용", null),
            PostModel("2", "예이~예이~예이~예이~예이~예이~예이~예이~예이~예이~", "2323", "하이", null),
            PostModel("왕십리에서 한잔 할사람~", "본인 키 180대 남자임", "2323", "우", null),
            PostModel("한잔해~", "에~~~~~~오~~!!에~~~~~~오~~!!에~~~~~~오~~!!에~~~~~~오~~!!에~~~~~~오~~!!에~~~~~~오~~!!", "2323", "종민", null),
            PostModel("5", "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", "2323", "야호~가게", null),
            PostModel("6", "ㅇㅇ??", "2323", "영", null),
            PostModel("7", "엥?", "2323", "아이고", null),
            PostModel("8", "너느은~", "2323", "하하", null),
            PostModel("9", "안되겠다~", "2323", "호호", null),
            PostModel( "10", "너느으으으은~너느으으으은~너느으으으은~너느으으으은~너느으으으은~너느으으으은~", "2323", "홍박사", null)
        )
    }

    private fun mapToMyPageModel(response: ResponseMyPageModel?): MyPageModel? {
        return if (response != null) {
            MyPageModel(
                name = response.name,
                birthday = response.birthday,
                gender = if (response.gender == "MALE") "남성" else "여성",
                phone = insertHyphens(response.phone)
            )
        } else null
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
                telephone = insertHyphens2(response.telephone),
                time = response.time.map {
                    RunTimeModel(
                        day = it.day,
                        startTime = it.startTime,
                        endTime = it.endTime
                    )
                },
                coupon = CouponModel(
                    name = response.coupon.title,
                    stampCount = response.coupon.cnt,
                    couponColor = response.coupon.couponColor,
                    fontColor = response.coupon.fontColor,
                    stampMax = 10,
                    stampImage = imageNullCheck(response.coupon.stampType)
                ),
                menuList = response.menus?.map {
                    MenuModel(
                        name = it.name,
                        description = it.description,
                        image = imageNullCheck(it.image),
                        isNew = it.isNew
                    )
                }
            )
        } else null
    }

    private fun todayRunTime(runTime: RunTimeModel) {

        val today = LocalDate.now().dayOfWeek.toString()
    }
    private fun mapToStoreCouponCountModelList(responseList: List<ResponseNearStoreModel>?): List<StoreCouponCountModel>? {
        return responseList?.map { response ->
            StoreCouponCountModel(
                id = response.storeId,
                image = imageNullCheck(response.storeImage),
                name = response.name,
                coupon = response.couponCnt
            )
        }
    }

    private fun mapToStoreInfoItemModelList(responseList: List<ResponsePopularStoreModel>?): List<StoreInfoItemModel>? {
        return responseList?.map { response ->
            StoreInfoItemModel(
                id = response.storeId,
                image = imageNullCheck(response.storeImage),
                name = response.name,
                address = response.location,
                description = response.description,
            )
        }
    }

    private fun mapToPostModelList(responseList: List<ResponseCommunityModel>?): List<PostModel>? {
        return responseList?.map { response ->
            PostModel(
                title = response.title,
                contents = response.noticeContent,
                date = response.createAt,
                storeName = response.storeName,
                imageUri = response.noticeImage.map {
                    Uri.parse(it)
                }
            )
        }
    }

    private fun imageNullCheck(uri: String?): Uri? {
        return if (uri != null) {
            Uri.parse(uri)
        } else {
            null
        }
    }

    private fun insertHyphens(input: String): String {
        val modifiedString = StringBuilder(input)
        modifiedString.insert(3, "-")
        modifiedString.insert(8, "-")
        return modifiedString.toString()
    }

    private fun insertHyphens2(input: String): String {
        val modifiedString = StringBuilder(input)
        modifiedString.insert(2, "-")
        modifiedString.insert(7, "-")
        return modifiedString.toString()
    }
}