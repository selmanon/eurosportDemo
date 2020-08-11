package com.tech.demo.dto

import com.google.gson.annotations.SerializedName

class ResponseDTO(
    @SerializedName("videos") var videos: List<VideoDTO>,
    @SerializedName("stories") var stories: List<StoryDTO>
)