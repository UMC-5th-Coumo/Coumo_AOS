package com.umc.coumo.domain.repository

interface LoginRepository {

    suspend fun postJoin(
        loginId: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        email: String,
        phone: String
    )
    suspend fun postLogin(loginId: String, password: String)
}