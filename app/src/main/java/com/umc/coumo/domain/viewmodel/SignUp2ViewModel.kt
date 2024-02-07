package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUp2ViewModel @Inject constructor() : ViewModel() {
    val spinnerGenderItems = listOf("남자", "여자")
    var selectedGenderPosition: Int = 0
    val spinnerEmailItems = listOf("naver.com", "google.com")
    var selectedEmailPostion: Int = 0

}