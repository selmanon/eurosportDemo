package com.tech.demo

import com.tech.demo.entity.NewsEntity
import com.tech.demo.mapper.StoryEntityMapper
import com.tech.demo.mapper.VideoEntityMapper
import com.tech.demo.service.EuroSportApiService
import com.tech.demo.store.INewsNetwork
import io.reactivex.Single
import javax.inject.Inject

class NewsNetworkImpl @Inject constructor(
    private val videoMapper: VideoEntityMapper,
    private val storyMapper: StoryEntityMapper,
    private val apiService: EuroSportApiService
) : INewsNetwork {

    override fun getFromNetwork(): Single<List<NewsEntity>> {
        return apiService.getNewsList().map { response ->
            listOf(response.videos.map {
                videoMapper.mapFromDto(it)
            }, response.stories.map {
                storyMapper.mapFromDTO(it)
            }).flatten()
        }
    }
}