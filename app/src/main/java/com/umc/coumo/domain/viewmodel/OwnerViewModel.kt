package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.repository.CoumoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OwnerViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel() {

    private val _countStamp = MutableLiveData<Int>(1);
    val countStamp: LiveData<Int> get() = _countStamp

    private val _countPayment = MutableLiveData<Int>(1);
    val countPayment: LiveData<Int> get() = _countPayment

    suspend fun postStampOwner(storeId: Int, customerId: Int,): Boolean {
        return repository.postOwnerStamp(storeId, customerId, _countStamp.value?:1)
    }

    suspend fun postPaymentOwner(storeId: Int, customerId: Int,): Boolean {
        return repository.postOwnerPayment(storeId, customerId, _countPayment.value?:1)
    }

    fun changeStampCount(pm: Boolean) {
        if (pm) {
            if ((_countStamp.value ?: 0) <= 10) {
                _countStamp.value = (_countStamp.value?:0) + 1
            }
        } else {
            if ((_countStamp.value ?: 0) > 0) {
                _countStamp.value = (_countStamp.value?:0) - 1
            }
        }
    }

    fun changeStampPayment(pm: Boolean) {
        if (pm) {
            if ((_countPayment.value ?: 0) <= 10) {
                _countPayment.value = (_countPayment.value?:0) + 1
            }
        } else {
            if ((_countPayment.value ?: 0) > 0) {
                _countPayment.value = (_countPayment.value?:0) - 1
            }
        }
    }
}