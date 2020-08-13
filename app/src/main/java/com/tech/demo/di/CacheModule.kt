package com.tech.demo.di

import android.app.Application
import com.tech.demo.NewsCacheImpl
import com.tech.demo.db.NewsDataBase
import com.tech.demo.store.INewsCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCacheDataSource(impl: NewsCacheImpl): INewsCache


    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): NewsDataBase {
            return NewsDataBase.getInstance(application)
        }
    }
}