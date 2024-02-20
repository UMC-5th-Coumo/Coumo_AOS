package com.umc.coumo.domain.viewmodel

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


    fun changeTab(tabType: CommunityTabType) {
        _currentTab.value = tabType
        viewModelScope.launch {
            _postList.value = null
            clearPage()
            _postList.value = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)
        }
    }

    suspend fun getCommunityList() {
        _postList.value = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)
    }

    fun setCurrentPost(item: PostModel) {
        _post.value = item
    }

    private fun clearPage() {
        _pageId.value = 1
    }

    fun addPage() {
        viewModelScope.launch {
            _pageId.value = (_pageId.value?:1) + 1
            val currentList = _postList.value
            val newList = repository.getCommunityPost(_currentTab.value?.api, 126.88804417884324 , 37.520786061099514, pageId.value?:1)

        }
    }
}