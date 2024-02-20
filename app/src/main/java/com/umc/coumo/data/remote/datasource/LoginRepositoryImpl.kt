package com.umc.coumo.data.remote.datasource

import android.util.Log
import com.umc.coumo.App
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.data.remote.model.request.RequestCheckDupIdModel
import com.umc.coumo.data.remote.model.request.RequestFindIdModel
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestJoinRequestVerificationModel
import com.umc.coumo.data.remote.model.request.RequestJoinVerifyCodeModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.request.RequestVerifyIdCodeModel
import com.umc.coumo.data.remote.model.response.ResponseCheckDupIdModel
import com.umc.coumo.data.remote.model.response.ResponseJoinModel
import com.umc.coumo.data.remote.model.response.ResponseLoginAsOwnerModel
import com.umc.coumo.data.remote.model.response.ResponseLoginModel
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

    override suspend fun postJoinRequestVerification(name: String, phone: String): Boolean {
        val data = loginApi.postJoinRequestVerification(RequestJoinRequestVerificationModel(name, phone))
        return data.isSuccessful
    }

    override suspend fun postJoinVerifyCode(phone: String, verificationCode: String): Boolean {
        val data = loginApi.postJoinVerifyCode(RequestJoinVerifyCodeModel(phone, verificationCode))
        return data.isSuccessful
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

    override suspend fun postFindIdRequestCode(name: String, phone: String): Boolean {
        val data = loginApi.postFindIdRequestCode(RequestFindIdModel(name, phone))
        return data.body()?.isSuccess ?: false
    }

    override suspend fun postVerifyIdCode(phone: String, verificationCode: String): String? {
        val data = loginApi.postVerifyIdCode(RequestVerifyIdCodeModel(phone, verificationCode))
        return data.body()?.result?.loginId
    }

    override suspend fun postLoginAsOwner(loginId: String, password: String): ResponseLoginAsOwnerModel? {
        val data = loginApi.postLoginAsOwner(RequestLoginModel(loginId, password))
        val token = data.body()?.result?.token
        App.prefs.setString("accessToken", token ?: "")
        App.prefs.setInt("ownerId", data.body()?.result?.ownerId ?: 0)
        return mapToResponseLoginAsOwner(data.body()?.result)
    }

    private fun mapToResponseLoginModel(response: ResponseLoginModel?): ResponseLoginModel? {
        return if (response != null) {
            ResponseLoginModel(
                customerId = response.customerId,
                token = response.token
            )
        }
        else null
    }

    private fun mapToResponseJoinModel(response: ResponseJoinModel?): ResponseJoinModel? {
        return if (response != null) {
            ResponseJoinModel(
                id = response.id,
                loginId = response.loginId,
                name = response.name,
                createAt = response.createAt?:""
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

    private fun mapToResponseLoginAsOwner(response: ResponseLoginAsOwnerModel?): ResponseLoginAsOwnerModel? {
        return if (response != null) {
            ResponseLoginAsOwnerModel(
                ownerId = response.ownerId,
                storeId = response.storeId,
                token = response.token,
                createAt = response.createAt?:"",
                write = response.write
            )
        }
        else null
    }
}