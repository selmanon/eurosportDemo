package com.tech.demo.dto

import com.google.gson.annotations.SerializedName

class StoryDTO(
    @SerializedName("title") var storyTitle: String,
    @SerializedName("teaser") val storyTeaser: String,
    @SerializedName("image") val storyImage: String,
    @SerializedName("date") val storyDate: Double,
    @SerializedName("author") val storyAuthor: String,
    @SerializedName("sport") val storySport: SportDTO
)