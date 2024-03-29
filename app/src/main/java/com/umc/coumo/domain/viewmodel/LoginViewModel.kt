package com.umc.coumo.domain.viewmodel

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _id = MutableLiveData<String>("")
    val id: LiveData<String> get() = _id

    private val _pw = MutableLiveData<String>("")
    val pw: LiveData<String> get() = _pw
    private val _isNotValidateAccount = MutableLiveData(false)
    val isNotValidateAccount: LiveData<Boolean> get() = _isNotValidateAccount

    private val _loginResult = MutableLiveData<Boolean>(false)
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _afterPressLoginBtn = MutableLiveData<Boolean>(false)
    val afterPressLoginBtn: LiveData<Boolean> get() = _afterPressLoginBtn

    private val _loginAs = MutableLiveData<String>("")
    val loginAs: LiveData<String> get() = _loginAs

    val loginAsCustomer: Boolean get() = (_loginAs.value.toString() == "customer")


    fun postLogin(loginId: String, password: String) {
        viewModelScope.launch {
            val response = repository.postLogin(loginId, password)
            _loginResult.value = (response != null)
        }
    }

    fun postLoginAsOwner(loginId: String, password: String) {
        viewModelScope.launch {
            val response = repository.postLoginAsOwner(loginId, password)
            _loginResult.value = (response != null)
        }
    }

    fun clearVars() {
        _isNotValidateAccount.value = false
        _loginResult.value = false
        _afterPressLoginBtn.value = false
    }
    fun trueAfterPressLoginBtn() { _afterPressLoginBtn.value = true }
    fun setLoginResult(bool : Boolean) { _loginResult.value = bool }
    fun setIsNotValidateAccount(bool: Boolean) { _isNotValidateAccount.value = bool }
    fun setLoginAs(s: String) { _loginAs.value = s }
}