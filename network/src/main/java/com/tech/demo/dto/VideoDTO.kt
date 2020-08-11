package com.tech.demo.dto

import com.google.gson.annotations.SerializedName

class VideoDTO(
    @SerializedName("title") var videoTitle: String,
    @SerializedName("thumb") var videoImage: String,
    @SerializedName("url") var videoUrl: String,
    @SerializedName("date") var videoDate: Double,
    @SerializedName("sport") var videoSport: SportDTO,
    @SerializedName("views") var videoViews: Long
)