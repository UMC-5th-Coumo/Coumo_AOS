package com.umc.coumo.domain.repository

interface LoginRepository {

    suspend fun postJoin()
    suspend fun postLogin()
}