package com.tech.demo.mapper

import com.tech.demo.dto.StoryDTO
import com.tech.demo.entity.StoryEntity
import javax.inject.Inject

class StoryEntityMapper @Inject constructor() {

    fun mapFromDTO(dto: StoryDTO): StoryEntity {

        return StoryEntity(
            dto.id,
            dto.storyTitle,
            dto.storyTeaser,
            dto.storyImage,
            dto.storyDate.toLong(),
            dto.storySport.SportName,
            dto.storyAuthor
        )
    }
}