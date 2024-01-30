package com.umc.coumo.domain.model

import android.net.Uri

data class StoreInfoItemModel(
    val id : Int,
    val image: Uri? = null,
    val name: String,
    val address: String,
    val content: String,
)
