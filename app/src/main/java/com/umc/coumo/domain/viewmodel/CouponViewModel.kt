package com.umc.coumo.domain.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.App.Companion.prefs
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CouponAlignType
import com.umc.coumo.utils.Constants.CUSTOMER_ID
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

    private val _currentQR = MutableLiveData<Uri?>()
    val currentQR: LiveData<Uri?> get() = _currentQR

    fun changeAlign(align: CouponAlignType) {
        _align.value = align
        getCouponList()
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

    fun getCouponList() {
        viewModelScope.launch {
            repository.getCouponList(align.value?:CouponAlignType.MOST)
        }
    }

    fun getCouponStore(storeId: Int) {
        viewModelScope.launch {
            repository.getCouponStore(storeId)
        }
    }

    fun getStampQR(storeId: Int) {
        _currentQR.value = Uri.parse("https://dev.coumo.shop/api/qr/customer/stamp/${prefs.getInt(CUSTOMER_ID,1)}/${storeId}")
    }

    fun getPaymentQR(storeId: Int) {
        Log.d("TEST http","${prefs.getInt(CUSTOMER_ID,1)}")
        _currentQR.value = Uri.parse("https://dev.coumo.shop/api/qr/customer/payment/${prefs.getInt(CUSTOMER_ID,1)}/${storeId}")
    }
}