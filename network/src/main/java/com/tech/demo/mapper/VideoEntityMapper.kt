package com.tech.demo.mapper

import com.tech.demo.dto.VideoDTO
import com.tech.demo.entity.VideoEntity
import javax.inject.Inject

class VideoEntityMapper @Inject constructor() {

    fun mapFromDto(dto: VideoDTO): VideoEntity {

        return VideoEntity(
            dto.videoTitle,
            dto.videoImage,
            dto.videoDate,
            dto.videoSport.SportName,
            dto.videoUrl,
            dto.videoViews
        )
    }
}