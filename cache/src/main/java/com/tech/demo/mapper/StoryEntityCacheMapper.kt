package com.tech.demo.mapper

import com.tech.demo.entity.StoryDataBaseEntity
import com.tech.demo.entity.StoryEntity
import javax.inject.Inject

class StoryEntityCacheMapper @Inject constructor() {

    fun mapFromDatabaseEntity(dbEntity: StoryDataBaseEntity) =
        StoryEntity(
            storyTitle = dbEntity.title,
            storyTeaser = dbEntity.teaser,
            storyImage = dbEntity.image,
            storyDate = dbEntity.date,
            storySport = dbEntity.sport,
            storyAuthor = dbEntity.author
        )


    fun mapToDatabaseEntity(entity: StoryEntity) =
        StoryDataBaseEntity(
            title = entity.storyTitle,
            teaser = entity.storyTeaser,
            image = entity.storyImage,
            date = entity.storyDate,
            sport = entity.storySport,
            author = entity.storyAuthor
        )
}