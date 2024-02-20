package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.domain.repository.LoginRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {
    private val _isValidatePassword = MutableLiveData(false)
    val isValidatePassword: LiveData<Boolean> get() = _isValidatePassword

    private val _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    fun postResetPassword(loginId: String, newPassword: String) {
        viewModelScope.launch {
            _isSuccess.value = repository.postResetPassword(loginId, newPassword)
        }
    }

    fun setIsValidatePassword(bool: Boolean) { _isValidatePassword.value = bool }
}