package com.umc.coumo.data.remote.datasource

import com.umc.coumo.App
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    //API Injection
    private val loginApi: LoginApi
): LoginRepository {


    override suspend fun postJoin() {
        loginApi.postJoin(RequestJoinModel())
    }

    override suspend fun postLogin(){
        val data = loginApi.postLogin(RequestLoginModel())
        App.prefs.setString("accessToken",data.body()?.result?.token?:"")
        App.prefs.setInt("customerId",data.body()?.result?.customerId?:0)
    }

}