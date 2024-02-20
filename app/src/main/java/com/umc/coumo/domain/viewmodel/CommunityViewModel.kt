package com.umc.coumo.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.coumo.domain.model.LocationLatLng
import com.umc.coumo.domain.model.PostItemModel
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

    private val _currentLocation = MutableLiveData<LocationLatLng>(LocationLatLng(126.88804417884324 , 37.520786061099514))
    val currentLocation: LiveData<LocationLatLng> get() = _currentLocation

    private val _post = MutableLiveData<PostModel>()
    val post: LiveData<PostModel> get() = _post

    private val _postAllList = MutableLiveData<List<PostItemModel>>(listOf())
    val postAllList: LiveData<List<PostItemModel>> get() = _postAllList

    private val _selectedPost = MutableLiveData<PostItemModel?>(null)
    val selectedPost: LiveData<PostItemModel?> get() = _selectedPost

    private val _isOnDetail = MutableLiveData<Boolean>(false)
    val isOnDetail: LiveData<Boolean> get() = _isOnDetail

    val marginValue: Int get() = if (isOnDetail.value == true) 0 else 32

    fun getCommunityAllList() {
        viewModelScope.launch {
            val response = repository.getCommunityAll(
                longitude = _currentLocation.value?.longitude!!,
                latitude = _currentLocation.value?.latitude!!,
                pageId = 0
            )
            _postAllList.value = _postAllList.value?.plus(mapToPostItemModelList(response!!))
        }
    }

    fun changeTab(tabType: CommunityTabType) {
        _currentTab.value = tabType
    }

    private fun mapToPostItemModel(model: PostModel, idx: Int?): PostItemModel {
        return PostItemModel(
            id = idx?:0,
            title = model.title,
            contents = model.contents,
            date = model.date,
            storeName = model.storeName,
            imageUri = model.imageUri
        )
    }

    fun mapToPostItemModelList(modelList: List<PostModel>): List<PostItemModel> {
        val itemList = mutableListOf<PostItemModel>()
        modelList.forEachIndexed { idx, item ->
            itemList.add(mapToPostItemModel(item, idx))
        }
        return itemList
    }

    fun setSelectedPost(post: PostItemModel?) { _selectedPost.value = post }
    fun setIsOnDetail(bool: Boolean) { _isOnDetail.value = bool }
}