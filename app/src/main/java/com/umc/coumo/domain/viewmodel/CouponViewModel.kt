package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CouponAlignType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel() {
    private val _align = MutableLiveData(CouponAlignType.MOST)
    val align: LiveData<CouponAlignType> get() = _align

    private val _couponList = MutableLiveData<List<CouponModel>>()
    val couponList: LiveData<List<CouponModel>> get() = _couponList

    private val _currentCoupon = MutableLiveData<CouponModel>()
    val currentCoupon: LiveData<CouponModel> get() = _currentCoupon

    private val _currentQR = MutableLiveData<String?>()
    val currentQR: LiveData<String?> get() = _currentQR

    fun changeAlign(align: CouponAlignType) {
        _align.value = align
    }

    fun testData() {
        val list = listOf(
            CouponModel(name = "왕십리 청춘카페", stampCount = 1, color = "?", stampMax = 10, stampImage = null),
            CouponModel(name = "왕십리 청춘카페2", stampCount = 8, color = "?", stampMax = 8, stampImage = null),
            CouponModel(name = "왕십리 청춘카페3", stampCount = 3, color = "?", stampMax = 12, stampImage = null),
            CouponModel(name = "왕십리 청춘카페4", stampCount = 7, color = "?", stampMax = 10, stampImage = null),
        )
        _couponList.value = list
    }

    fun postCustomerStamp(storeId: Int) {
        viewModelScope.launch {
            _currentQR.value = repository.postStampCustomer(storeId)
        }
    }

    fun postCustomerPayment(storeId: Int) {
        viewModelScope.launch {
            //_currentQR.value = repository.postPaymentCustomer(storeId)
        }
    }

}