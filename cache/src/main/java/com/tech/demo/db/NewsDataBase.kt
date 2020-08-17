package com.tech.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tech.demo.dao.NewsDao
import com.tech.demo.entity.StoryDataBaseEntity
import com.tech.demo.entity.VideoDataBaseEntity
import javax.inject.Inject

@Database(
    entities = [StoryDataBaseEntity::class, VideoDataBaseEntity::class],
    version = Constants.DataBase.DATABASE_VERSION
)
abstract class NewsDataBase @Inject constructor() : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: NewsDataBase? = null
        private val lock = Any()

        fun getInstance(context: Context): NewsDataBase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsDataBase::class.java, "news.db")
                            .build()
                    }
                    return INSTANCE as NewsDataBase
                }
            }
            return INSTANCE as NewsDataBase
        }
    }

}