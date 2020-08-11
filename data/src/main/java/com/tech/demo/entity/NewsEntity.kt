package com.tech.demo.entity

import com.tech.demo.domain.NewsDomain
import com.tech.demo.domain.NewsType
import com.tech.demo.domain.StoryType
import com.tech.demo.domain.VideoType

sealed class NewsEntity {
    abstract val type: NewsType
}

data class StoryEntity(
    val storyTitle: String,
    val storyTeaser: String,
    val storyImage: String,
    val storyDate: Double,
    val storySport: String
) : NewsEntity() {
    override val type: NewsType
        get() = StoryType
}

data class VideoEntity(
    val videoTitle: String,
    val videoTeaser: String,
    val videoImage: String,
    val videoDate: Double,
    val videoSport: String,
    val videoUrl: String,
    val videoViews: Long
) : NewsEntity() {
    override val type: NewsType
        get() = VideoType
}