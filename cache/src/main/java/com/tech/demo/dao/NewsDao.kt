package com.tech.demo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tech.demo.db.Constants
import com.tech.demo.entity.StoryDataBaseEntity
import com.tech.demo.entity.VideoDataBaseEntity
import io.reactivex.Flowable

@Dao
abstract class NewsDao {

    @Query(Constants.Videos.QUERY_VIDEOS)
    @JvmSuppressWildcards
    abstract fun getVideos(): Flowable<List<VideoDataBaseEntity>>

    @Query(Constants.Stories.QUERY_STORIES)
    @JvmSuppressWildcards
    abstract fun getStories(): Flowable<List<StoryDataBaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertVideos(videos: List<VideoDataBaseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertStories(stories: List<StoryDataBaseEntity>)
}