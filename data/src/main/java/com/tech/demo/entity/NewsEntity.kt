package com.tech.demo.entity

import com.tech.demo.domain.NewsType
import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType

sealed class NewsEntity {
    abstract val type: NewsType
}

data class StoryEntity(
    val id: Int,
    val storyTitle: String,
    val storyTeaser: String,
    val storyImage: String,
    val storyDate: Long,
    val storySport: String,
    val storyAuthor: String
) : NewsEntity() {
    override val type: NewsType
        get() = StoryType
}

data class VideoEntity(
    val id: Int,
    val videoTitle: String,
    val videoImage: String,
    val videoDate: Long,
    val videoSport: String,
    val videoUrl: String,
    val videoViews: Long
) : NewsEntity() {
    override val type: NewsType
        get() = VideoType
}