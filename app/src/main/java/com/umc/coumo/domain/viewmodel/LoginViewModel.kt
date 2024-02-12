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
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _id = MutableLiveData<String>("")
    val id: LiveData<String> get() = _id

    private val _pw = MutableLiveData<String>("")
    val pw: LiveData<String> get() = _pw

    //TODO(테스트 코드)
    fun postJoin(loginId: String, password: String) {
        viewModelScope.launch {
            //repository.postJoin(loginId, password)
        }
    }

    //TODO(테스트 코드)
    fun postLogin(loginId: String, password: String) {
        viewModelScope.launch {
            repository.postLogin(loginId, password)
        }
    }
}