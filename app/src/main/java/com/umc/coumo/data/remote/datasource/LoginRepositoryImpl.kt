package com.umc.coumo.data.remote.datasource

import android.net.Uri
import com.umc.coumo.App
import com.umc.coumo.data.remote.api.LoginApi
import com.umc.coumo.data.remote.model.request.RequestJoinModel
import com.umc.coumo.data.remote.model.request.RequestLoginModel
import com.umc.coumo.data.remote.model.response.ResponsePopularStoreModel
import com.umc.coumo.domain.model.StoreInfoItemModel
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
        App.prefs.setString("accessToken",data.body()?.token?:"")
    }

    private fun mapToStoreInfoItemModelList(responseList: List<ResponsePopularStoreModel>?): List<StoreInfoItemModel>? {
        return responseList?.map { response ->
            StoreInfoItemModel(
                id = response.storeId,
                image = Uri.parse(response.storeImage),
                name = response.name,
                address = response.location,
                description = response.description,
            )
        }
    }

}