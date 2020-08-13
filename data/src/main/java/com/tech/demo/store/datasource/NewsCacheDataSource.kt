package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import com.tech.demo.store.INewsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class NewsCacheDataSource @Inject constructor(
    private val cache: INewsCache
) : INewsDataSource {

    override fun getNewsList(): Observable<List<NewsEntity>> {
        return cache.getFromCache()
    }

    override fun saveNewsList(list: List<NewsEntity>): Completable {
        return cache.saveToCache(list)
    }
}