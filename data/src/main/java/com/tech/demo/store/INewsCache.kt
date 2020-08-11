package com.tech.demo.store

import com.tech.demo.entity.NewsEntity
import io.reactivex.Single

interface INewsCache {

    fun getFromCache(): Single<List<NewsEntity>>
}