package com.tech.demo.domain

sealed class NewsDomain {
    protected abstract val type: NewsType
}

sealed class NewsType
object VideoType : NewsType()
object StoryType : NewsType()

data class StoryDomain(
    val storyTitle: String,
    val storyTeaser: String,
    val storyImage: String,
    val storyDate: Long,
    val storyAuthor: String,
    val storySport: String
) : NewsDomain() {
    override val type: NewsType
        get() = StoryType
}

data class VideoDomain(
    val videoTitle: String,
    val videoImage: String,
    val videoDate: Long,
    val videoSport: String,
    val videoUrl: String,
    val videoViews: Long
) : NewsDomain() {
    override val type: NewsType
        get() = VideoType
}