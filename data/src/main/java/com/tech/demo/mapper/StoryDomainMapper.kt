package com.tech.demo.mapper

import com.tech.demo.domain.StoryDomain
import com.tech.demo.entity.StoryEntity
import javax.inject.Inject

class StoryDomainMapper @Inject constructor() {

    fun mapFromEntity(entity: StoryEntity) =
        StoryDomain(
            storyTitle = entity.storyTitle,
            storyTeaser = entity.storyTeaser,
            storyImage = entity.storyImage,
            storyDate = entity.storyDate,
            storySport = entity.storySport
        )
}