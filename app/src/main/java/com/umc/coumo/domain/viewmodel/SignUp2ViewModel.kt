package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUp2ViewModel @Inject constructor() : ViewModel() {

    private val _isValidateName = MutableLiveData(false)
    val isValidateName: LiveData<Boolean> get() = _isValidateName

    private val _isValidateBirthday = MutableLiveData(false)
    val isValidateBirthday: LiveData<Boolean> get() = _isValidateBirthday

    private val _isValidateGender = MutableLiveData(false)
    val isValidateGender: LiveData<Boolean> get() = _isValidateGender

    private val _isValidateId = MutableLiveData(false)
    val isValidateId: LiveData<Boolean> get() = _isValidateId

    private val _isValidatePassword = MutableLiveData(false)
    val isValidatePassword: LiveData<Boolean> get() = _isValidatePassword

    private val _isValidateRePassword = MutableLiveData(false)
    val isValidateRePassword: LiveData<Boolean> get() = _isValidateRePassword

    private val _isValidateEmail = MutableLiveData(false)
    val isValidateEmail: LiveData<Boolean> get() = _isValidateEmail

    private val _isValidatePhone = MutableLiveData(false)
    val isValidatePhone: LiveData<Boolean> get() = _isValidatePhone

    private val _isWrong = MutableLiveData(false)
    val isWrong: LiveData<Boolean> get() = _isWrong

    fun setIsValidateName(bool: Boolean) { _isValidateName.value = bool }
    fun setIsValidateBirthday(bool: Boolean) { _isValidateBirthday.value = bool }
    fun setIsValidateGender(bool: Boolean) { _isValidateGender.value = bool }
    fun setIsValidateId(bool: Boolean) { _isValidateId.value = bool }
    fun setIsValidatePassword(bool: Boolean) { _isValidatePassword.value = bool }
    fun setIsValidateRePassword(bool: Boolean) { _isValidateRePassword.value = bool }
    fun setIsValidateEmail(bool: Boolean) { _isValidateEmail.value = bool }
    fun setIsValidatePhone(bool: Boolean) { _isValidatePhone.value = bool }
    fun setIsWrong(bool: Boolean) { _isWrong.value = bool }

    fun isOkOnViewModel() : Boolean {
        return (isValidateName.value!! &&
                isValidateBirthday.value!! &&
                isValidateId.value!! &&
                isValidatePassword.value!! &&
                isValidateRePassword.value!! &&
                isValidateGender.value!! &&
                isValidateEmail.value!! &&
                isValidatePhone.value!!)
    }


    val spinnerGenderItems = listOf("남자", "여자")
    var selectedGenderPosition: Int = 0
    val spinnerEmailItems = listOf("naver.com", "google.com")
    var selectedEmailPostion: Int = 0

}