package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.type.DetailTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _currentTab = MutableLiveData(DetailTabType.INFO)
    val currentTab: LiveData<DetailTabType> get() = _currentTab

    private val _menuList = MutableLiveData<List<MenuModel>>()
    val menuList: LiveData<List<MenuModel>> get() = _menuList

    private val _storeData = MutableLiveData<StoreInfoModel>()
    val storeData: LiveData<StoreInfoModel> get() = _storeData

    init {
        testData()
    }

    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }

    fun loadStoreData() {
        // TODO( API 에서 데이터 가져오기 )
        testData()
    }

    private fun testData() {
        _storeData.value = StoreInfoModel(
            name = "가게 이름",
            description = "가게 정보",
            location = "가게 위치 정보",
            longitude = 126.12,
            latitude = 36.12,
            image = null,
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