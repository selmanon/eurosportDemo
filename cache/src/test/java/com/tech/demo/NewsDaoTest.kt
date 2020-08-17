package com.tech.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.flextrade.jfixture.JFixture
import com.tech.demo.db.NewsDataBase
import com.tech.demo.entity.StoryDataBaseEntity
import com.tech.demo.entity.VideoDataBaseEntity
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fixture: JFixture

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        NewsDataBase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @Before
    fun setup() {
        fixture = JFixture()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetVideos() {
        val video1 = fixture.create(VideoDataBaseEntity::class.java)
        val video2 = fixture.create(VideoDataBaseEntity::class.java)
        val fixtList = listOf(video1, video2)
        database.newsDao().insertVideos(fixtList)
        database.newsDao().getVideos().map {
            assertEquals(it, fixtList)
            assertEquals(it.size, fixtList.size)
        }
    }

    @Test
    fun testGetStories() {
        val story1 = fixture.create(StoryDataBaseEntity::class.java)
        val story2 = fixture.create(StoryDataBaseEntity::class.java)
        val fixtList = listOf(story1, story2)
        database.newsDao().insertStories(fixtList)
        database.newsDao().getStories().test().assertValue(fixtList)
    }
}