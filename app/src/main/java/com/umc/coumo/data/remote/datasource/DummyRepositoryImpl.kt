package com.umc.coumo.data.remote.datasource

import com.umc.coumo.domain.repository.DummyRepository
import com.umc.coumo.data.remote.api.DummyApi
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    //API Injection
    val dummyApi: DummyApi
): DummyRepository {
}