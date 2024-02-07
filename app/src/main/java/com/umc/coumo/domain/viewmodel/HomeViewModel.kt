package com.umc.coumo.domain.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.domain.model.StoreInfoModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CategoryType
import com.umc.coumo.domain.type.DetailTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel() {

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
    private val _popularStoreList = MutableLiveData<List<StoreInfoItemModel>>()
    val popularStoreList: LiveData<List<StoreInfoItemModel>> get() = _popularStoreList

    private val _longitude = MutableLiveData<Double>(87.02629637)
    val longitude: LiveData<Double> get() = _longitude

    private val _latitude = MutableLiveData<Double>(37.500075)
    val latitude: LiveData<Double> get() = _latitude

    fun getPopularStoreList() {
        viewModelScope.launch {
            _popularStoreList.value = repository.getPopularStoreList(longitude = _longitude.value!!, latitude = _latitude.value!!) //빈 값 없으니 이렇게 처리
        }
    }

    private fun getNearStoreList(category: CategoryType?) {
        viewModelScope.launch {
            _nearStoreList.value = repository.getNearStoreList(category = category, longitude = _longitude.value!!, latitude = _latitude.value!!)
        }
    }


    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }

    fun selectCategory(category: CategoryType) {
        _category.value = category
        getNearStoreList(category)
    }

    fun loadStoreData(storeId: Int) {
        // TODO( API 에서 데이터 가져오기 )
        Log.d("OKHTTP_TEST","$storeId")
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