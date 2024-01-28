package com.umc.coumo.domain.model

import android.net.Uri

data class MenuModel(
    val id: Int,
    val name: String,
    val content: String,
    val image: Uri? = null,)