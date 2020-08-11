package com.tech.demo.store

import com.tech.demo.entity.NewsEntity
import io.reactivex.Single

interface INewsNetwork {

    fun getFromNetwork(): Single<List<NewsEntity>>
}