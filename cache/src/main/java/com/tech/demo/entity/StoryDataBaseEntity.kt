package com.tech.demo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tech.demo.db.Constants

@Entity(tableName = Constants.Stories.TABLE_NAME)
data class StoryDataBaseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var teaser: String,
    var image: String,
    var date: Double,
    var sport: String,
    var author:String
)