package com.tech.demo.mapper

import android.annotation.SuppressLint
import com.tech.demo.domain.StoryDomain
import com.tech.demo.model.StoryModel
import java.text.SimpleDateFormat
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


    @SuppressLint("SimpleDateFormat")
    private fun getStoryDate(timestamp: Double): String {
        val c: Calendar = Calendar.getInstance()
        c.timeInMillis = timestamp.toLong()
        val d: Date = c.getTime()
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return sdf.format(d)
    }
}