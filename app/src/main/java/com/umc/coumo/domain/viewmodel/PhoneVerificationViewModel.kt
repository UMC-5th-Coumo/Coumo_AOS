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

    private val _isValidateUser = MutableLiveData<Boolean?>(null)
    val isValidateUser: LiveData<Boolean?> get() = _isValidateUser

    private val _isValidateCode = MutableLiveData<Boolean?>(null)
    val isValidateCode: LiveData<Boolean?> get() = _isValidateCode

    private val _isFindForId = MutableLiveData<Boolean>(true)
    val isFindForId: LiveData<Boolean> get() = _isFindForId

    var foundId: String? = null

    fun postFindIdRequestCode(name: String, phone: String) {
        _isValidateUser.value = null
        viewModelScope.launch {
            _isValidateUser.value = repository.postFindIdRequestCode(name, phone)
        }
    }
    fun postVerifyIdCode(phone: String, verificationCode: String) {
        _isValidateCode.value = null
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
    fun setIsValidateCode(bool: Boolean?) { _isValidateCode.value = bool }

    fun clearVars() {
        _isValidateCode.value = null
        _isValidateUser.value = null
    }

}