package com.tech.demo.mapper

import com.tech.demo.domain.VideoDomain
import com.tech.demo.model.VideoModel
import javax.inject.Inject

class VideoModelMapper @Inject constructor() {

    fun map(domain: VideoDomain): VideoModel {
        return VideoModel(
            domain.videoTitle,
            domain.videoImage,
            domain.videoDate,
            domain.videoSport,
            domain.videoUrl,
            domain.videoViews
        )
    }
}