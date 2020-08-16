package com.tech.demo

import com.flextrade.jfixture.JFixture
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.tech.demo.domain.StoryDomain
import com.tech.demo.domain.VideoDomain
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.mapper.StoryDomainMapper
import com.tech.demo.mapper.VideoDomainMapper
import com.tech.demo.store.NewsDataSourceFactory
import com.tech.demo.store.datasource.INewsDataSource
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsRepositoryTest {

    @Mock
    lateinit var mockFactory: NewsDataSourceFactory

    @Mock
    lateinit var mockStoryDomainMapper: StoryDomainMapper

    @Mock
    lateinit var mockVideoDomainMapper: VideoDomainMapper

    @Mock
    lateinit var mockNewsDataSource:INewsDataSource

    lateinit var sut: NewsRepository
    lateinit var fixture: JFixture

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fixture = JFixture()
        sut = NewsRepository(mockFactory, mockStoryDomainMapper, mockVideoDomainMapper)
    }

    @Test
    fun getNews() {

        val fixtStoryEntity1 = stubStoryEntity()
        val fixtVideoEntity = stubVideoEntity()
        val fixtStoryEntity2 = stubStoryEntity()

        val fixtStoryDomain1 = stubStoryDomain()
        val fixtStoryDomain2 = stubStoryDomain()
        val fixtVideoDomain = stubVideoDomain()


        whenever(mockFactory.getDataSource(any())).thenReturn(mockNewsDataSource)
        whenever(mockNewsDataSource.getNewsList()).thenReturn(
            Observable.just(
                listOf(
                    fixtStoryEntity1,
                    fixtVideoEntity,
                    fixtStoryEntity2
                )
            )
        )

        whenever(mockStoryDomainMapper.mapFromEntity(fixtStoryEntity1)).thenReturn(fixtStoryDomain1)
        whenever(mockVideoDomainMapper.mapFromEntity(fixtVideoEntity)).thenReturn(fixtVideoDomain)
        whenever(mockStoryDomainMapper.mapFromEntity(fixtStoryEntity2)).thenReturn(fixtStoryDomain2)


        sut.getNews().test()
            .assertValue(listOf(fixtStoryDomain1, fixtVideoDomain, fixtStoryDomain2))
    }


    private fun stubStoryEntity() = fixture.create(StoryEntity::class.java)

    private fun stubVideoEntity() = fixture.create(VideoEntity::class.java)

    private fun stubStoryDomain() = fixture.create(StoryDomain::class.java)

    private fun stubVideoDomain() = fixture.create(VideoDomain::class.java)
}