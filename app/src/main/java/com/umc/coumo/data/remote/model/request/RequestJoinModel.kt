package com.umc.coumo.data.remote.model.request

data class RequestJoinModel(
    val loginId: String = "testId",
    val password: String = "testPassword",
    val name: String = "테스트 사용자",
    val birthday: String = "2000.01.01",
    val gender: String = "MALE",
    val email: String = "test@test.com",
    val phone: String = "010-1234-5678"
)
