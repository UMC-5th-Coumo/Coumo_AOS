package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.domain.type.DetailTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _currentTab = MutableLiveData(DetailTabType.INFO)
    val currentTab: LiveData<DetailTabType> get() = _currentTab

    private val _menuList = MutableLiveData<List<MenuModel>>()
    val menuList: LiveData<List<MenuModel>> get() = _menuList

    init {
        testData()
    }

    fun changeTab(tab: DetailTabType) {
        _currentTab.value = tab
    }

    private fun testData() {
        val list = listOf(
            MenuModel(id = 0, name = "아이스 바닐라 라떼", content = "Tall: 4,800\nGrande: 6,800\nTrenta: 6,800"),
            MenuModel(id = 1, name = "아이스 바닐라 라떼2", content = "Tall: 4,800\nGrande: 6,800\nTrenta: 6,800", isNew = true),
            MenuModel(id = 2, name = "아이스 바닐라 라떼3", content = "Tall: 4,800\nGrande: 6,800\nTrenta: 6,800",),
            MenuModel(id = 3, name = "아이스 바닐라 라떼4", content = "Tall: 4,800\nGrande: 6,800\nTrenta: 6,800",),
            MenuModel(id = 4, name = "아이스 바닐라 라떼5", content = "Tall: 4,800\nGrande: 6,800\nTrenta: 6,800",),
        )
        _menuList.value = list
    }
}