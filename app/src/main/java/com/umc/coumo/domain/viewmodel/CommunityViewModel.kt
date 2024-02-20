package com.umc.coumo.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.PostModel
import com.umc.coumo.domain.repository.CoumoRepository
import com.umc.coumo.domain.type.CommunityTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repository: CoumoRepository
): ViewModel(){
    private val _currentTab = MutableLiveData(CommunityTabType.ALL)
    val currentTab: LiveData<CommunityTabType> get() = _currentTab

    private val _post = MutableLiveData<PostModel>()
    val post: LiveData<PostModel> get() = _post

    private val _postList = MutableLiveData<List<PostModel>?>()
    val postList: LiveData<List<PostModel>?> get() = _postList

    private val _pageId = MutableLiveData<Int>(1)
    val pageId: LiveData<Int> get() = _pageId

    private val _canLoad = MutableLiveData<Boolean>(true)
    val canLoad: LiveData<Boolean> get() = _canLoad

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    fun changeTab(tabType: CommunityTabType) {
        _currentTab.value = tabType
        viewModelScope.launch {
            _postList.value = null
            clearPage()
            _postList.value = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)
        }
    }

    fun getCommunityList() {
        viewModelScope.launch {
            _isLoading.value = true
            _postList.value = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)

            _canLoad.value = (_postList.value?.size?:0) >= 10 //10개가 안 찼으면 로드 불가
            _isLoading.value = false
        }
    }

    fun setCurrentPost(item: PostModel) {
        _post.value = item
    }

    private fun clearPage() {
        _pageId.value = 1
        _canLoad.value = true
    }

    fun addPage() {
        Log.d("TEST scroll http", "페이지 증가!! ${_canLoad.value}")
        viewModelScope.launch {
            if (_canLoad.value != false) {
                _pageId.value = (_pageId.value?:1) + 1
                val currentList = _postList.value?: mutableListOf()
                val newList = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)?: mutableListOf()
                if (newList.size == 10) {
                    _canLoad.value = true
                    _postList.value = currentList + newList
                } else {
                    _canLoad.value = false
                    _postList.value = currentList + newList
                }
            }
        }
    }
}