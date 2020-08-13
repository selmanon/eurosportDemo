package com.tech.demo.store

import com.tech.demo.entity.NewsEntity
import io.reactivex.Observable

interface INewsNetwork {

    fun getFromNetwork(): Observable<List<NewsEntity>>
}