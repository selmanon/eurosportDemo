package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import com.tech.demo.store.INewsCache
import io.reactivex.Single
import javax.inject.Inject

class NewsCacheDataSource @Inject constructor(
    private val cache: INewsCache
) : INewsDataSource {
    override fun getNewsList(): Single<List<NewsEntity>> {
        return cache.getFromCache()
    }
}