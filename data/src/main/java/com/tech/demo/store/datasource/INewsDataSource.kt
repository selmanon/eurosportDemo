package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import io.reactivex.Single

interface INewsDataSource {
    fun getNewsList(): Single<List<NewsEntity>>
}