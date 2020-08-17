package com.tech.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.flextrade.jfixture.JFixture
import com.nhaarman.mockito_kotlin.whenever
import com.tech.demo.db.NewsDataBase
import com.tech.demo.entity.*
import com.tech.demo.mapper.StoryEntityCacheMapper
import com.tech.demo.mapper.VideoEntityCacheMapper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class NewsCacheImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockVideoMapper: VideoEntityCacheMapper

    @Mock
    lateinit var mockStoryMapper: StoryEntityCacheMapper

    lateinit var fixture: JFixture
    lateinit var sut: NewsCacheImpl

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        NewsDataBase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = NewsCacheImpl(database, mockVideoMapper, mockStoryMapper)
        fixture = JFixture()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun getFromCache() {

        val video = fixture.create(VideoDataBaseEntity::class.java)
        val videoEntity = fixture.create(VideoEntity::class.java)
        val fixtListVideos = listOf(video)
        database.newsDao().insertVideos(fixtListVideos)

        val story = fixture.create(StoryDataBaseEntity::class.java)
        val storyEntity = fixture.create(StoryEntity::class.java)
        val fixtListStories = listOf(story)
        database.newsDao().insertStories(fixtListStories)

        whenever(mockVideoMapper.mapFromDatabaseEntity(video)).thenReturn(videoEntity)
        whenever(mockStoryMapper.mapFromDatabaseEntity(story)).thenReturn(storyEntity)

        sut.getFromCache().test().assertValue(listOf(videoEntity, storyEntity))
    }

    @Test
    fun saveToCacheCompletes() {

        val video = fixture.create(VideoDataBaseEntity::class.java)
        val videoEntity = fixture.create(VideoEntity::class.java)
        val fixtListVideos = listOf(videoEntity)

        val story = fixture.create(StoryDataBaseEntity::class.java)
        val storyEntity = fixture.create(StoryEntity::class.java)
        val fixtListStories = listOf(storyEntity)

        whenever(mockVideoMapper.mapToDatabaseEntity(videoEntity)).thenReturn(video)
        whenever(mockStoryMapper.mapToDatabaseEntity(storyEntity)).thenReturn(story)

        sut.saveToCache(listOf(fixtListVideos, fixtListStories).flatten()).test().assertComplete()
    }
}