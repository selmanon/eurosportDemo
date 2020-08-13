package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import com.tech.demo.store.INewsNetwork
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class NewsNetworkDataSource @Inject constructor(
    private val network: INewsNetwork
) : INewsDataSource {
    override fun getNewsList(): Observable<List<NewsEntity>> {
        return network.getFromNetwork()
    }

    override fun saveNewsList(list: List<NewsEntity>): Completable {
        throw  UnsupportedOperationException("this operation is not supported here")
    }
}