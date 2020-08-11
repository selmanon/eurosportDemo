package com.tech.demo.mapper

import com.tech.demo.domain.VideoDomain
import com.tech.demo.entity.VideoEntity
import javax.inject.Inject

class VideoDomainMapper @Inject constructor(){

    fun mapFromEntity(entity: VideoEntity) =
        VideoDomain(
            videoTitle = entity.videoTitle,
            videoTeaser = entity.videoTeaser,
            videoImage = entity.videoImage,
            videoDate = entity.videoDate,
            videoSport = entity.videoSport,
            videoUrl = entity.videoUrl,
            videoViews = entity.videoViews
        )
}