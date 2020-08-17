package com.tech.demo.store.datasource

import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType
import com.tech.demo.entity.NewsEntity
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.store.INewsNetwork
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class NewsNetworkDataSource @Inject constructor(
    private val network: INewsNetwork
) : INewsDataSource {
    override fun getNewsList(): Observable<List<NewsEntity>> {

        val list = network.getFromNetwork()

        val sortedStories = list.map {
            it.filter { entity ->
                entity.type == StoryType
            }
        }.map {
            it.sortedByDescending {
                (it as StoryEntity).storyDate
            }
        }

        val sortedVideos = list.map {
            it.filter { entity ->
                entity.type == VideoType
            }
        }.map {
            it.sortedByDescending {
                (it as VideoEntity).videoDate
            }
        }

        return Observable.zip(sortedStories, sortedVideos,  BiFunction<List<NewsEntity>, List<NewsEntity>, List<NewsEntity>> { stories, videos ->
          listOf(stories, videos).flatten()
        })
    }

    override fun saveNewsList(list: List<NewsEntity>): Completable {
        throw  UnsupportedOperationException("this operation is not supported here")
    }
}