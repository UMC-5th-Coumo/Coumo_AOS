package com.umc.coumo.di

import com.umc.coumo.data.remote.api.CoumoApi
import com.umc.coumo.data.remote.datasource.CoumoRepositoryImpl
import com.umc.coumo.domain.repository.CoumoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(
        coumoApi: CoumoApi
    ): CoumoRepository = CoumoRepositoryImpl(coumoApi)
}