package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.type.DetailTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _currentTab = MutableLiveData(DetailTabType.INFO)
    val currentTab: LiveData<DetailTabType> get() = _currentTab

    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }
}