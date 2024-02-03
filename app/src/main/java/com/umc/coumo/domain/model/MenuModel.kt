package com.umc.coumo.domain.model

import android.net.Uri

data class MenuModel(
    val name: String,
    val description: String,
    val image: Uri? = null,
    val isNew: Boolean = false
)