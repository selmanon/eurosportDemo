package com.tech.demo

import com.tech.demo.db.NewsDataBase
import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType
import com.tech.demo.entity.NewsEntity
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.mapper.StoryEntityCacheMapper
import com.tech.demo.mapper.VideoEntityCacheMapper
import com.tech.demo.store.INewsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class NewsCacheImpl @Inject constructor(
    private val newsDataBase: NewsDataBase,
    private val videoMapper: VideoEntityCacheMapper,
    private val storyMapper: StoryEntityCacheMapper
) : INewsCache {

    override fun getFromCache(): Observable<List<NewsEntity>> {

        return Observable.zip(
            newsDataBase.newsDao().getVideos().toObservable().map { list ->
                list.map {
                    videoMapper.mapFromDatabaseEntity(it)
                }
            },
            newsDataBase.newsDao().getStories().toObservable().map { list ->
                list.map {
                    storyMapper.mapFromDatabaseEntity(it)
                }
            },
            BiFunction<List<VideoEntity>, List<StoryEntity>, List<NewsEntity>> { videos, stories ->
                listOf(videos, stories).flatten()
            })
    }

    override fun saveToCache(list: List<NewsEntity>): Completable {
        return Completable.defer {
            newsDataBase.newsDao().insertStories(list.filter {
                it.type == StoryType
            }.map {
                storyMapper.mapToDatabaseEntity(it as StoryEntity)
            })

            newsDataBase.newsDao().insertVideos(list.filter {
                it.type == VideoType
            }.map {
                videoMapper.mapToDatabaseEntity(it as VideoEntity)
            })
            Completable.complete()
        }
    }
}