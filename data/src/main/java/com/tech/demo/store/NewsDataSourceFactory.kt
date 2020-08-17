package com.tech.demo.store

import com.tech.demo.store.datasource.INewsDataSource
import com.tech.demo.store.datasource.NewsCacheDataSource
import com.tech.demo.store.datasource.NewsNetworkDataSource
import javax.inject.Inject

class NewsDataSourceFactory @Inject constructor(
    private val networkDataSource: NewsNetworkDataSource,
    private val cacheDataSource: NewsCacheDataSource,
    private val connectionChecker: IConnectivityChecker
) {

    fun getDataSource(): INewsDataSource {
        return if (!connectionChecker.isConnected())
            cacheDataSource
        else
            networkDataSource
    }

    fun getCacheDataSource() = cacheDataSource

}