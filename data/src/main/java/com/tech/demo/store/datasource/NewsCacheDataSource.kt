package com.tech.demo.store.datasource

import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType
import com.tech.demo.entity.NewsEntity
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.store.INewsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class NewsCacheDataSource @Inject constructor(
    private val cache: INewsCache
) : INewsDataSource {

    override fun getNewsList(): Observable<List<NewsEntity>> {
        val list = cache.getFromCache()

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

        return Observable.zip(
            sortedStories,
            sortedVideos,
            BiFunction<List<NewsEntity>, List<NewsEntity>, List<NewsEntity>> { stories, videos ->
                mergeData(stories, videos)
            })
    }

    override fun saveNewsList(list: List<NewsEntity>): Completable {
        return cache.saveToCache(list)
    }

    private fun mergeData(
        stories: List<NewsEntity>,
        videos: List<NewsEntity>
    ): List<NewsEntity> {
        if (stories.size < videos.size)
            return mergeData(videos, stories)
        val list = mutableListOf<NewsEntity>()

        stories.forEachIndexed { index, story ->
            list.add(story)
            if (index < videos.size) list.add(videos[index])
        }

        return list
    }
}