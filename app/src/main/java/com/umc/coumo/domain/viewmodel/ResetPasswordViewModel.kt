package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(): ViewModel() {
    private val _isValidatePassword = MutableLiveData(false)
    val isValidatePassword: LiveData<Boolean> get() = _isValidatePassword

    fun setIsValidatePassword(bool: Boolean) { _isValidatePassword.value = bool }
}