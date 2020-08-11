package com.tech.demo.repository

import com.tech.demo.domain.NewsDomain
import io.reactivex.Single

interface INewsRepository {

    fun getNews(): Single<List<NewsDomain>>
}