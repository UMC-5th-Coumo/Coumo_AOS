package com.umc.coumo.domain.viewmodel

import android.location.Address
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.LocationLatLng
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

    private val _currentStoreId = MutableLiveData<Int>()
    val currentStoreId: LiveData<Int> get() = _currentStoreId

    //카테고리
    private val _category = MutableLiveData<CategoryType>(CategoryType.CAFE)
    val category: LiveData<CategoryType> get() = _category

    //근처 매장 리스트
    private val _nearStoreList = MutableLiveData<List<StoreCouponCountModel>>()
    val nearStoreList: LiveData<List<StoreCouponCountModel>> get() = _nearStoreList

    //인기 매장 리스트
    private val _popularStoreList = MutableLiveData<List<StoreInfoItemModel>>()
    val popularStoreList: LiveData<List<StoreInfoItemModel>> get() = _popularStoreList

    private val _currentLocation = MutableLiveData<LocationLatLng>(LocationLatLng(126.88804417884324 , 37.520786061099514))
    val currentLocation: LiveData<LocationLatLng> get() = _currentLocation

    private val _currentAddress = MutableLiveData<String>()
    val currentAddress: LiveData<String> get() = _currentAddress

    private val _timeDropDown = MutableLiveData<Boolean>(false)
    val timeDropDown: LiveData<Boolean> get() = _timeDropDown

    private val _currentPage = MutableLiveData<Int>(0)
    val currentPage: LiveData<Int> get() = _currentPage

    fun setCurrentLocation(longitude: Double, latitude: Double ) {
        _currentLocation.value = LocationLatLng(longitude, latitude)
    }

    fun setCurrentAddress(address: List<Address>?) {
        _currentAddress.value = address?.get(0)?.let { it.adminArea + " "+ it.subLocality + " "+ it.thoroughfare }
        Log.d("TEST Address","${address?.get(0)}")
    }

    fun getPopularStoreList() {
        viewModelScope.launch {
            _popularStoreList.value = repository.getPopularStoreList(longitude = _currentLocation.value?.longitude!!, latitude = _currentLocation.value?.latitude!!) //빈 값 없으니 이렇게 처리
        }
    }

    private fun getNearStoreList(category: CategoryType?) {
        viewModelScope.launch {
            _nearStoreList.value = repository.getNearStoreList(category = category,longitude = _currentLocation.value?.longitude!!, latitude = _currentLocation.value?.latitude!!)
        }
    }

    private fun getCouponStore(storeId: Int) {
        viewModelScope.launch {
            repository.getCouponStore(storeId)
        }
    }

    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }

    fun selectCategory(category: CategoryType) {
        _category.value = category
        getNearStoreList(category)
    }

    fun changeRunTime() {
        _timeDropDown.value = !_timeDropDown.value!!
    }

    fun loadStoreData(storeId: Int) {
        viewModelScope.launch {
            repository.getStoreData(storeId).let {
                if (it != null) {
                    _currentStoreId.value = storeId
                    getCouponStore(storeId)
                    _timeDropDown.value = false
                    _storeData.value = it
                } else {

                      //값을 못 받아 왔을 때, 빈 값 처리
                }
            }
        }
    }

    fun resetPage() {
        _currentPage.value = 0
        Log.d("HTTP 페이지", "페이지 초기화")
    }

    fun addPage() {
        _currentPage.value = _currentPage.value?.plus(1)
    }

}