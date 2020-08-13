package com.tech.demo.di

import com.tech.demo.NewsNetworkImpl
import com.tech.demo.service.EuroSportApiService
import com.tech.demo.service.EuroSportApiServiceFactory
import com.tech.demo.store.INewsNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun provideNetworkDataSource(impl: NewsNetworkImpl): INewsNetwork

    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun provideNetworkService(): EuroSportApiService {
            return EuroSportApiServiceFactory.provideService()
        }
    }
}