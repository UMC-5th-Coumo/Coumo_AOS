package com.umc.coumo.domain.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.type.DetailTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _currentTab = MutableLiveData(DetailTabType.INFO)
    val currentTab: LiveData<DetailTabType> get() = _currentTab

    private val _storeData = MutableLiveData<StoreInfoModel>()
    val storeData: LiveData<StoreInfoModel> get() = _storeData

    //카테고리
    private val _category = MutableLiveData<CategoryType>(CategoryType.CAFE)
    val category: LiveData<CategoryType> get() = _category

    //근처 매장 리스트
    private val _nearStoreList = MutableLiveData<List<StoreCouponCountModel>>()
    val nearStoreList: LiveData<List<StoreCouponCountModel>> get() = _nearStoreList

    //인기 매장 리스트
    private val _popularStoreList = MutableLiveData<List<StoreCouponCountModel>>()
    val popularStoreList: LiveData<List<StoreCouponCountModel>> get() = _popularStoreList


    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }

    fun selectCategory(category: CategoryType) {
        _category.value = category
        //리스트 요청
    }

    fun loadStoreData() {
        // TODO( API 에서 데이터 가져오기 )
        testData()
    }

    private fun testData() {
        _storeData.value = StoreInfoModel(
            name = "앙떼띠 로스터리(강남점)",
            description = "양떼띠 로스터리는 2017년에 오픈한 강남의 유명 카페입니다. 강남역 직장인들을 위해 평일 오전 7시~9시에\n" +
                    "아메리카노 2000원 이벤트를 진행 중입니다.",
            location = "가게 위치 정보",
            longitude = 127.02629637,
            latitude = 37.500075,
            image = listOf(Uri.parse(""),
                ),
            coupon = CouponModel("?",1,"1",1,null),
            menuList = listOf(
                MenuModel("메뉴 이름1","메뉴 정보1"),
                MenuModel("메뉴 이름2","메뉴 정보2", isNew = true),
                MenuModel("메뉴 이름3","메뉴 정보3"),
                MenuModel("메뉴 이름4","메뉴 정보4"),
            )
        )
    }
}