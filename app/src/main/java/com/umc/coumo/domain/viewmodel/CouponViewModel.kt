package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.type.CouponAlignType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CouponViewModel @Inject constructor(): ViewModel() {
    private val _align = MutableLiveData(CouponAlignType.MOST)
    val align: LiveData<CouponAlignType> get() = _align

    fun changeAlign(align: CouponAlignType) {
        _align.value = align
    }
}