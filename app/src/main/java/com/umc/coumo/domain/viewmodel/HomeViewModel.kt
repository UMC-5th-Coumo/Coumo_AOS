package com.umc.coumo.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _currentLongitude = MutableLiveData<Double>(127.00091673551657)
    val currentLongitude: LiveData<Double> get() = _currentLongitude

    private val _currentLatitude = MutableLiveData<Double>(37.55800312017019)
    val currentLatitude: LiveData<Double> get() = _currentLatitude

    fun getPopularStoreList() {
        viewModelScope.launch {
            _popularStoreList.value = repository.getPopularStoreList(longitude = _currentLongitude.value!!, latitude = _currentLatitude.value!!) //빈 값 없으니 이렇게 처리
        }
    }

    private fun getNearStoreList(category: CategoryType?) {
        viewModelScope.launch {
            _nearStoreList.value = repository.getNearStoreList(category = category, longitude = _currentLongitude.value!!, latitude = _currentLatitude.value!!)
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
        viewModelScope.launch {
            repository.getStoreData(storeId).let {
                if (it != null) {
                    _storeData.value = it
                } else {
                    listOf<StoreInfoModel>() //값을 못 받아 왔을 때, 빈 값 처리
                }
            }
        }
        Log.d("OKHTTP_TEST","$storeId \n${_storeData.value}")
    }

}