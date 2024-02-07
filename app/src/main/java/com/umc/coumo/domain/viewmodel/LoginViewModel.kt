package com.umc.coumo.domain.viewmodel

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
    //TODO(테스트 코드)
    fun postJoin() {
        viewModelScope.launch {
            repository.postJoin()
        }
    }

    //TODO(테스트 코드)
    fun postLogin() {
        viewModelScope.launch {
            repository.postLogin()
        }
    }
}