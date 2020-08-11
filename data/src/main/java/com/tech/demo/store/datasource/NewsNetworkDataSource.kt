package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import com.tech.demo.store.INewsNetwork
import io.reactivex.Single
import javax.inject.Inject

class NewsNetworkDataSource @Inject constructor(
    private val network: INewsNetwork
) : INewsDataSource {
    override fun getNewsList(): Single<List<NewsEntity>> {
        return network.getFromNetwork()
    }
}