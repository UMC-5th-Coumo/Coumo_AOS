package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUp1ViewModel @Inject constructor(): ViewModel() {
    private val _isExpandedService = MutableLiveData(false)
    val isExpandedService: LiveData<Boolean> get() = _isExpandedService

    private val _isExpandedPrivacy = MutableLiveData(false)
    val isExpandedPrivacy: LiveData<Boolean> get() = _isExpandedPrivacy

    private val _isExpandedMarketing = MutableLiveData(false)
    val isExpandedMarketing: LiveData<Boolean> get() = _isExpandedMarketing

    fun changeIsExpandedService() { _isExpandedService.value = !_isExpandedService.value!! }
    fun changeIsExpandedPrivacy() { _isExpandedPrivacy.value = !_isExpandedPrivacy.value!! }
    fun changeIsExpandedMarketing() { _isExpandedMarketing.value = !_isExpandedMarketing.value!! }


}