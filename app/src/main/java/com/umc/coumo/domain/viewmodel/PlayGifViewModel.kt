package com.umc.coumo.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PlayGifViewModel@Inject constructor() : ViewModel() {
    private val _isPlayingClap = MutableLiveData<Boolean>(false)
    val isPlayingClap : LiveData<Boolean> get() = _isPlayingClap

    fun setIsPlayingClap(bool: Boolean) { _isPlayingClap.value = bool  }
}