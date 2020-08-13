package com.tech.demo.di

import com.tech.demo.NewsRepository
import com.tech.demo.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideNewsRepository(impl: NewsRepository): INewsRepository
}