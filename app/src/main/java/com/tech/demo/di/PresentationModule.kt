package com.tech.demo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tech.demo.ConnectivityCheckerImpl
import com.tech.demo.ViewModelFactory
import com.tech.demo.store.IConnectivityChecker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
@InstallIn(ApplicationComponent::class)
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(com.tech.demo.NewsViewModel::class)
    abstract fun bindNewsViewModel(
        viewModel: com.tech.demo.NewsViewModel
    ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindConnectivityChecker(impl: ConnectivityCheckerImpl): IConnectivityChecker
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)