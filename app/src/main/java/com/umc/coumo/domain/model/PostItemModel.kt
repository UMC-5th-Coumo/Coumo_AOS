package com.umc.coumo.domain.model

import android.net.Uri

data class PostItemModel(
    var id: Int,
    val title: String,
    val contents: String,
    val date: String,
    val storeName: String,
    val imageUri: List<Uri?>?
)
