package com.tech.demo.store.datasource

import com.tech.demo.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface INewsDataSource {

    fun getNewsList(): Observable<List<NewsEntity>>

    fun saveNewsList(list: List<NewsEntity>): Completable
}