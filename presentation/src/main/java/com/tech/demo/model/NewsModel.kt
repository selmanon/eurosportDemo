package com.tech.demo.model


sealed class NewsModel {
    abstract val type: NewsType
}

sealed class NewsType
object VideoType : NewsType()
object StoryType : NewsType()


data class StoryModel(
    val storyTitle: String,
    val storyTeaser: String,
    val storyImage: String,
    val storyDate: String,
    val storySport: String,
    val storyAuthor:String
) : NewsModel() {
    override val type: NewsType
        get() = StoryType
}

data class VideoModel(
    val videoTitle: String,
    val videoImage: String,
    val videoDate: String,
    val videoSport: String,
    val videoUrl: String,
    val videoViews: Long
) : NewsModel() {
    override val type: NewsType
        get() = VideoType
}