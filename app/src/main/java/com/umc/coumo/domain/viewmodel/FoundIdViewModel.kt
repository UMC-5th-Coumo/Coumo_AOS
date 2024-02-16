package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.LoginRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

class FoundIdViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {
    fun postFindId(name: String, phone: String) {
        viewModelScope.launch {
            val response = repository.postFindIdRequestCode(name, phone)
        }
    }
}