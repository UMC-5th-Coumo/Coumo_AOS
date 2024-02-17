package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.CoumoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OwnerViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel() {

    suspend fun postStampOwner(storeId: Int, customerId: Int, stampCnt: Int): Boolean {
        return repository.postOwnerStamp(storeId, customerId, stampCnt)
    }

    suspend fun postPaymentOwner(storeId: Int, customerId: Int, stampCnt: Int): Boolean {
        return repository.postOwnerPayment(storeId, customerId, stampCnt)
    }
}