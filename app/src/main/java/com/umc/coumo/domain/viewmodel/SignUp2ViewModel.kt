package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SignUp2ViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _isValidateName = MutableLiveData(false)
    val isValidateName: LiveData<Boolean> get() = _isValidateName

    private val _isValidateBirthday = MutableLiveData(false)
    val isValidateBirthday: LiveData<Boolean> get() = _isValidateBirthday

    private val _isValidateId = MutableLiveData(false)
    val isValidateId: LiveData<Boolean> get() = _isValidateId

    private val _isValidatePassword = MutableLiveData(false)
    val isValidatePassword: LiveData<Boolean> get() = _isValidatePassword

    private val _isValidateRePassword = MutableLiveData(false)
    val isValidateRePassword: LiveData<Boolean> get() = _isValidateRePassword

    private val _isValidateEmail = MutableLiveData(false)
    val isValidateEmail: LiveData<Boolean> get() = _isValidateEmail

    private val _isValidatePhone = MutableLiveData<Boolean?>(null)
    val isValidatePhone: LiveData<Boolean?> get() = _isValidatePhone

    private val _isSignUpSuccess = MutableLiveData<Boolean?>(null)
    val isSignUpSuccess: LiveData<Boolean?> get() = _isSignUpSuccess

    private val _isSuccessSendCode = MutableLiveData<Boolean?>(null)
    val isSuccessSendCode: LiveData<Boolean?> get() = _isSuccessSendCode

    fun setIsValidateName(bool: Boolean) { _isValidateName.value = bool }
    fun setIsValidateBirthday(bool: Boolean) { _isValidateBirthday.value = bool }
    fun setIsValidateId(bool: Boolean) { _isValidateId.value = bool }
    fun setIsValidatePassword(bool: Boolean) { _isValidatePassword.value = bool }
    fun setIsValidateRePassword(bool: Boolean) { _isValidateRePassword.value = bool }
    fun setIsValidateEmail(bool: Boolean) { _isValidateEmail.value = bool }
    fun setIsValidatePhone(bool: Boolean?) { _isValidatePhone.value = bool }
    fun setIsSignUpSuccess(bool: Boolean?) { _isSignUpSuccess.value = bool }
    fun setIsSuccessSendCode(bool: Boolean?) { _isSuccessSendCode.value = bool }

    fun isOkOnViewModel() : Boolean {
        return (isValidateName.value!! &&
                isValidateBirthday.value!! &&
                isValidateId.value!! &&
                isValidatePassword.value!! &&
                isValidateRePassword.value!! &&
                isValidateEmail.value!! &&
                isValidatePhone.value?:false)
    }

    fun clearVars() {
        _isValidateName.value = false
        _isValidateBirthday.value = false
        _isValidateId.value = false
        _isValidatePassword.value = false
        _isValidateRePassword.value = false
        _isValidateEmail.value = false
        _isValidatePhone.value = null
        _isSignUpSuccess.value = null
        _isSuccessSendCode.value = null
    }
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
            val response = repository.postJoin(loginId, password, name, birthday, gender, email, phone)
            _isSignUpSuccess.value = (response != null)
        }
    }

    fun postCheckDupId(loginId: String) {
        viewModelScope.launch {
            val response = repository.postCheckDupId(loginId)
            _isValidateId.value = (response?.loginId.toString() == loginId)
        }
    }

    fun postJoinRequestVerification(name: String, phone: String) {
        viewModelScope.launch {
            _isSuccessSendCode.value = repository.postJoinRequestVerification(name, phone)
        }
    }

    fun postJoinVerifyCode(phone: String, verificationCode: String) {
        viewModelScope.launch {
            _isValidatePhone.value = repository.postJoinVerifyCode(phone, verificationCode)
        }
    }

    val spinnerGenderItems = listOf("남자", "여자")
    var selectedGenderPosition: Int = 0
    val spinnerEmailItems = listOf("naver.com", "google.com", "daum.net", "hanmail.net", "hanyang.ac.kr" )
    var selectedEmailPosition: Int = 0


}