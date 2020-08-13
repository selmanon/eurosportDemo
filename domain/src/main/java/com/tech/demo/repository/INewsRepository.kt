package com.tech.demo.repository

import com.tech.demo.domain.NewsDomain
import io.reactivex.Observable

interface INewsRepository {

    fun getNews(): Observable<List<NewsDomain>>
}