package com.tech.demo

import com.tech.demo.domain.NewsDomain
import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.mapper.StoryDomainMapper
import com.tech.demo.mapper.VideoDomainMapper
import com.tech.demo.repository.INewsRepository
import com.tech.demo.store.NewsDataSourceFactory
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val factory: NewsDataSourceFactory,
    private val storyDomainMapper: StoryDomainMapper,
    private val videoDomainMapper: VideoDomainMapper
) : INewsRepository {

    override fun getNews(): Single<List<NewsDomain>> {
        return factory.getDataSource(false)
            .getNewsList().map { news ->
            news.map {
                when (it.type) {
                    StoryType -> storyDomainMapper.mapFromEntity(it as StoryEntity)
                    VideoType -> videoDomainMapper.mapFromEntity(it as VideoEntity)
                }
            }
        }
    }
}