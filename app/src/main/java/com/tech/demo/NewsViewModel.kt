package com.tech.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech.demo.domain.NewsDomain
import com.tech.demo.interactor.NewsInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {

    var disposables: CompositeDisposable = CompositeDisposable()

    init {
        fetch()
    }

    private var allNews: MutableLiveData<List<NewsDomain>> = MutableLiveData()


    fun fetch() {
        disposables.add(
            interactor.getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allNews.postValue(it)
                }, {
                    Log.e("Error", it.localizedMessage)
                })
        )
    }

    fun getNews() = allNews

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}