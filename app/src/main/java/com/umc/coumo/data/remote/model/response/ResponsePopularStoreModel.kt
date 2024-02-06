package com.umc.coumo.data.remote.model.response

data class ResponsePopularStoreModel(
    val storeId: Int,
    val name: String,
    val location: String,
    val description: String,
    val storeImage: String
)

/**
 * Expected BEGIN_ARRAY but was BEGIN_OBJECT at line
 * 오류로 인하여 객체화 시켜야 함
 * */
data class ResponsePopularStoreListModel(
    val result: List<ResponsePopularStoreModel>
)
