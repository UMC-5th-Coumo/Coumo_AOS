package com.umc.coumo.data.remote.datasource

import com.umc.coumo.App
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.data.remote.model.request.RequestCheckDupIdModel
import com.umc.coumo.data.remote.model.request.RequestFindIdModel
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.request.RequestVerifyIdCode
import com.umc.coumo.data.remote.model.response.ResponseCheckDupIdModel
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
    ): ResponseJoinModel? {
        val data = loginApi.postJoin(RequestJoinModel(
            loginId,
            password,
            name,
            birthday,
            gender,
            email,
            phone
        ))
        return mapToResponseJoinModel(data.body()?.result)
    }

    override suspend fun postLogin(loginId: String, password: String): ResponseLoginModel? {
        val data = loginApi.postLogin(RequestLoginModel(loginId, password))
        val customerId = data.body()?.result?.customerId
        val token = data.body()?.result?.token
        App.prefs.setString("accessToken", token ?: "")
        App.prefs.setInt("customerId", customerId ?: 0)
        return mapToResponseLoginModel(data.body()?.result)
    }

    override suspend fun postCheckDupId(loginId: String): ResponseCheckDupIdModel? {
        val data = loginApi.postCheckDupId(RequestCheckDupIdModel(loginId))
        return mapToResponseCheckDupIdModel(data.body()?.result)
    }

    override suspend fun postFindId(name: String, phone: String): Boolean {
        val data = loginApi.postFindId(RequestFindIdModel(name, phone))
        return data.isSuccessful
    }

    override suspend fun postVerifyIdCode(phone: String, verificationCode: String): String? {
        val data = loginApi.postVerifyIdCode(RequestVerifyIdCode(phone, verificationCode))
        return if (data.isSuccessful) data.body()?.result.toString()
        else null
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

    private fun mapToResponseJoinModel(response: ResponseJoinModel?): ResponseJoinModel? {
        return if (response != null) {
            ResponseJoinModel(
                id = response.id.toInt(),
                loginId = response.loginId.toString(),
                name = response.name.toString(),
                createAt = response.createAt.toString()
            )
        }
        else null
    }

    private fun mapToResponseCheckDupIdModel(response: ResponseCheckDupIdModel?): ResponseCheckDupIdModel? {
        return if (response != null) {
            ResponseCheckDupIdModel(loginId = response.loginId.toString())
        }
        else null
    }
}