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

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _afterPressLoginBtn = MutableLiveData<Boolean>(false)
    val afterPressLoginBtn: LiveData<Boolean> get() = _afterPressLoginBtn

    //TODO(테스트 코드)
    fun postJoin(
        loginId: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        email: String,
        phone: String
    ) {
        viewModelScope.launch {
            repository.postJoin(loginId, password, name, birthday, gender, email, phone)
        }
    }

    //TODO(테스트 코드)
    fun postLogin(loginId: String, password: String) {
        viewModelScope.launch {
            val response = repository.postLogin(loginId, password)
            _loginResult.value = (response != null)
        }
    }

    fun trueAfterPressLoginBtn() { _afterPressLoginBtn.value = true }

    fun setIsNotValidateAccount(bool: Boolean) { _isNotValidateAccount.value = bool }
}