package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneVerificationViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _isValidateUser = MutableLiveData(false)
    val isValidateUser: LiveData<Boolean> get() = _isValidateUser

    private val _isValidateCode = MutableLiveData(false)
    val isValidateCode: LiveData<Boolean> get() = _isValidateCode

    private val _isFindForId = MutableLiveData<Boolean>(true)
    val isFindForId: LiveData<Boolean> get() = _isFindForId
    private val _afterPressVerificationBtn = MutableLiveData(false)
    val afterPressVerificationBtn: LiveData<Boolean> get() = _afterPressVerificationBtn
    private val _afterPressNextBtn = MutableLiveData(false)
    val afterPressNextBtn: LiveData<Boolean> get() = _afterPressNextBtn

    var foundId: String? = null

    fun postFindId(name: String, phone: String) {
        viewModelScope.launch {
            _isValidateUser.value = repository.postFindId(name, phone)
        }
    }
    fun postVerifyIdCode(phone: String, verificationCode: String) {
        viewModelScope.launch {
            val response = repository.postVerifyIdCode(phone, verificationCode)
            if (response != null) {
                foundId = response
                _isValidateCode.value = true
            }
            else {
                _isValidateCode.value = false
            }
        }
    }


    fun setIsFindForId(bool: Boolean) { _isFindForId.value = bool }
    fun trueAfterPressVerificationBtn() { _afterPressVerificationBtn.value = true }
    fun trueAfterPressNextBtn() { _afterPressNextBtn.value = true }

    fun clearVars() {
        _isValidateCode.value = false
        _isValidateUser.value = false
    }

}