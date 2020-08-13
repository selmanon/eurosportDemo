package com.tech.demo.interactor

import com.tech.demo.domain.NewsDomain
import com.tech.demo.repository.INewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val repository: INewsRepository) {

    fun getNewsList(): Observable<List<NewsDomain>> {
        return repository.getNews()
    }
}