package com.tech.demo.mapper

import android.annotation.SuppressLint
import com.tech.demo.domain.StoryDomain
import com.tech.demo.model.StoryModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject

class StoryModelMapper @Inject constructor() {

    fun map(domain: StoryDomain): StoryModel {
        return StoryModel(
            domain.storyTitle,
            domain.storyTeaser,
            domain.storyImage,
            getStoryDate(domain.storyDate),
            domain.storySport,
            domain.storyAuthor
        )
    }


    @SuppressLint("NewApi")
    private fun getStoryDate(timestamp: Long) = Instant.ofEpochSecond( timestamp ).toString()
}