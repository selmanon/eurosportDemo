package com.tech.demo.mapper

import com.tech.demo.entity.VideoDataBaseEntity
import com.tech.demo.entity.VideoEntity
import javax.inject.Inject

class VideoEntityCacheMapper @Inject constructor() {

    fun mapFromDatabaseEntity(dbEntity: VideoDataBaseEntity) =
        VideoEntity(
            id = dbEntity.id,
            videoTitle = dbEntity.title,
            videoImage = dbEntity.image,
            videoDate = dbEntity.date,
            videoSport = dbEntity.sport,
            videoUrl = dbEntity.url,
            videoViews = dbEntity.views
        )


    fun mapToDatabaseEntity(entity: VideoEntity) =
        VideoDataBaseEntity(
            id = entity.id,
            title = entity.videoTitle,
            image = entity.videoImage,
            date = entity.videoDate,
            sport = entity.videoSport,
            url = entity.videoUrl,
            views = entity.videoViews
        )

}