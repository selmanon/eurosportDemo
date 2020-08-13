package com.tech.demo.store

import com.tech.demo.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface INewsCache {

    fun getFromCache(): Observable<List<NewsEntity>>

    fun saveToCache(list: List<NewsEntity>): Completable

    fun areNewsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}