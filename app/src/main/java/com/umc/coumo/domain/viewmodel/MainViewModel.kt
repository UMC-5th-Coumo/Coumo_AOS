package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.repository.LoginRepository
import com.umc.coumo.domain.type.TabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {
    private val _currentPageIndex = MutableLiveData<TabType>(TabType.HOME)
    val currentPageIndex: LiveData<TabType> get() = _currentPageIndex

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

    fun changePageIndex(type: TabType) {
        _currentPageIndex.value = type
    }
}