package com.umc.coumo.domain.repository

import com.umc.coumo.data.remote.model.response.ResponseCheckDupIdModel
import com.umc.coumo.data.remote.model.response.ResponseJoinModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel

interface LoginRepository {

    suspend fun postJoin(
        loginId: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        email: String,
        phone: String
    ): ResponseJoinModel?

    suspend fun postLogin(loginId: String, password: String): ResponseLoginModel?

    suspend fun postCheckDupId(loginId: String): ResponseCheckDupIdModel?
}