package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.domain.type.CouponAlignType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CouponViewModel @Inject constructor(): ViewModel() {
    private val _align = MutableLiveData(CouponAlignType.MOST)
    val align: LiveData<CouponAlignType> get() = _align

    private val _couponList = MutableLiveData<List<CouponModel>>()
    val couponList: LiveData<List<CouponModel>> get() = _couponList

    fun changeAlign(align: CouponAlignType) {
        _align.value = align
    }

    fun testData() {
        val list = listOf(
            CouponModel(name = "왕십리 청춘카페", stampCount = 1, color = "?", stampMax = 10, stampImage = null),
            CouponModel(name = "왕십리 청춘카페2", stampCount = 2, color = "?", stampMax = 10, stampImage = null),
            CouponModel(name = "왕십리 청춘카페3", stampCount = 3, color = "?", stampMax = 10, stampImage = null),
            CouponModel(name = "왕십리 청춘카페4", stampCount = 3, color = "?", stampMax = 10, stampImage = null),
        )
        _couponList.value = list
    }
}