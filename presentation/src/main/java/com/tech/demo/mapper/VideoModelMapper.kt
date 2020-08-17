package com.tech.demo.mapper

import android.annotation.SuppressLint
import com.tech.demo.domain.VideoDomain
import com.tech.demo.model.VideoModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject

class VideoModelMapper @Inject constructor() {

    fun map(domain: VideoDomain): VideoModel {
        return VideoModel(
            domain.videoTitle,
            domain.videoImage,
            getVideoDate(domain.videoDate),
            domain.videoSport,
            domain.videoUrl,
            domain.videoViews
        )
    }


    @SuppressLint("NewApi")
    private fun getVideoDate(timestamp: Long) = Instant.ofEpochSecond( timestamp ).toString()
}