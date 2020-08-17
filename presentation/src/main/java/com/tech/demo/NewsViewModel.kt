package com.tech.demo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech.demo.domain.NewsDomain
import com.tech.demo.domain.StoryDomain
import com.tech.demo.domain.VideoDomain
import com.tech.demo.interactor.NewsInteractor
import com.tech.demo.mapper.StoryModelMapper
import com.tech.demo.mapper.VideoModelMapper
import com.tech.demo.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    private val videoModelMapper: VideoModelMapper,
    private val storyModelMapper: StoryModelMapper
) : ViewModel() {

    var disposables: CompositeDisposable = CompositeDisposable()

    init {
        fetch()
    }

    private var allNews: MutableLiveData<List<NewsModel>> = MutableLiveData()


    fun fetch() {
        disposables.add(
            interactor.getNewsList()
                .subscribeOn(Schedulers.io())
                .map {
                    it.map {
                        when(it){
                            is VideoDomain -> videoModelMapper.map(it)
                            is StoryDomain -> storyModelMapper.map(it)
                        }
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allNews.postValue(it)
                }, {
                    Log.e("Error", it.toString())
                })
        )
    }


    fun getNews() = allNews

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}