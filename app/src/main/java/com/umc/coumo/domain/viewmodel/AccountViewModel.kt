package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(): ViewModel(){
    private val _isExpanded = MutableLiveData(false)
    val isExpanded: LiveData<Boolean> get() = _isExpanded

    fun changeIsExpanded() {
        _isExpanded.value = !_isExpanded.value!!
    }
}