package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.type.CommunityTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(): ViewModel(){
    private val _currentTab = MutableLiveData(CommunityTabType.ALL)
    val currentTab: LiveData<CommunityTabType> get() = _currentTab

    fun changeTab(tabType: CommunityTabType) {
        _currentTab.value = tabType
    }
}