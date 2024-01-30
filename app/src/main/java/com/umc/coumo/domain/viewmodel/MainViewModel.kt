package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.type.TabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _currentPageIndex = MutableLiveData<TabType>(TabType.HOME)
    val currentPageIndex: LiveData<TabType> get() = _currentPageIndex



    fun changePageIndex(type: TabType) {
        _currentPageIndex.value = type
    }
}