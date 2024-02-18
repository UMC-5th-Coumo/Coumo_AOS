package com.umc.coumo.domain.viewmodel

import android.net.Uri
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

    private val _currentCoupon = MutableLiveData<CouponModel>(CouponModel(name = "", stampCount = 0, stampMax = 10, stampImage = null))
    val currentCoupon: LiveData<CouponModel> get() = _currentCoupon

    private val _currentQR = MutableLiveData<Uri?>()
    val currentQR: LiveData<Uri?> get() = _currentQR

    private val _currentStoreId = MutableLiveData<Int>()
    val currentStoreId: LiveData<Int> get() = _currentStoreId

    init {
        getCouponList()
    }

    fun changeAlign(align: CouponAlignType) {
        _align.value = align
        getCouponList()
    }

    fun setCurrentCoupon(couponModel: CouponModel) {
        _currentCoupon.value = couponModel
    }

    fun getCouponList() {
        viewModelScope.launch {
            _couponList.value = repository.getCouponList(align.value?:CouponAlignType.MOST)
        }
    }

    fun setCurrentStoreId(storeId: Int) {
        _currentStoreId.value = storeId
    }

    fun getCouponStore(storeId: Int) {
        viewModelScope.launch {
            repository.getCouponStore(storeId)
        }
    }

    fun getStampQR() {
        _currentQR.value = Uri.parse("https://dev.coumo.shop/api/qr/customer/stamp/${prefs.getInt(CUSTOMER_ID,0)}/${currentStoreId.value}")
    }

    fun getPaymentQR() {
        _currentQR.value = Uri.parse("https://dev.coumo.shop/api/qr/customer/payment/${prefs.getInt(CUSTOMER_ID,0)}/${currentStoreId.value}")
    }
}