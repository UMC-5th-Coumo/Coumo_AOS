package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.MyPageModel
import com.umc.coumo.domain.repository.CoumoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel(){
    private val _isExpanded = MutableLiveData(false)
    val isExpanded: LiveData<Boolean> get() = _isExpanded

    private val _accountData = MutableLiveData<MyPageModel>()
    val accountData: LiveData<MyPageModel> get() = _accountData

    fun changeIsExpanded() {
        _isExpanded.value = !_isExpanded.value!!
    }

    fun getMyPage() {
        viewModelScope.launch {
            _accountData.value = repository.getMyPage()
        }
    }
}