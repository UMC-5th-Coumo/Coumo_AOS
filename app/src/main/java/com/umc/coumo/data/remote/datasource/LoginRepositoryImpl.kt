package com.umc.coumo.data.remote.datasource

import com.umc.coumo.App
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.response.ResponseJoinModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel
import com.umc.coumo.data.remote.model.response.ResponseModel
import com.umc.coumo.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    //API Injection
    private val loginApi: LoginApi
): LoginRepository {


    override suspend fun postJoin(
        loginId: String,
        password: String,
        name: String,
        birthday: String,
        gender: String,
        email: String,
        phone: String
    ) {
        //val data = loginApi.postJoin(RequestJoinModel(
        //    loginId,
        //    password,
        //    name,
        //    birthday,
        //    gender,
        //    email,
        //    phone
        //))
    }

    override suspend fun postLogin(loginId: String, password: String): ResponseLoginModel? {
        val data = loginApi.postLogin(RequestLoginModel(loginId, password))
        val customerId = data.body()?.result?.customerId
        val token = data.body()?.result?.token
        App.prefs.setString("accessToken", token ?: "")
        App.prefs.setInt("customerId", customerId ?: 0)
        return mapToResponseLoginModel(data.body()?.result)
    }

    private fun mapToResponseLoginModel(response: ResponseLoginModel?): ResponseLoginModel? {
        return if (response != null) {
            ResponseLoginModel(
                customerId = response.customerId.toInt(),
                token = response.customerId.toString()
            )
        }
        else null
    }

}