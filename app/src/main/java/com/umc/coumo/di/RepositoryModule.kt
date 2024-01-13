package com.umc.coumo.di

import com.umc.coumo.domain.repository.DummyRepository
import com.umc.coumo.data.remote.api.DummyApi
import com.umc.coumo.data.remote.datasource.DummyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(
        dummyApi: DummyApi
    ): DummyRepository = DummyRepositoryImpl(dummyApi)
}