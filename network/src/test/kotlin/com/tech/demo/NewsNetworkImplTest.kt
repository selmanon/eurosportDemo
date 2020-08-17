package com.tech.demo

import com.flextrade.jfixture.JFixture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tech.demo.dto.ResponseDTO
import com.tech.demo.dto.StoryDTO
import com.tech.demo.dto.VideoDTO
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.mapper.StoryEntityMapper
import com.tech.demo.mapper.VideoEntityMapper
import com.tech.demo.service.EuroSportApiService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class NewsNetworkImplTest {

    @Mock
    lateinit var mockVideoMapper: VideoEntityMapper

    @Mock
    lateinit var mockStoryMapper: StoryEntityMapper

    @Mock
    lateinit var mockApiService: EuroSportApiService

    lateinit var sut: NewsNetworkImpl
    lateinit var fixture: JFixture

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = NewsNetworkImpl(mockVideoMapper, mockStoryMapper, mockApiService)
        fixture = JFixture()
    }

    @Test
    fun getFromNetwork_data() {
        val fixtVideoDTO = fixture.create(VideoDTO::class.java)
        val fixtStoryDTO = fixture.create(StoryDTO::class.java)

        val fixtResponse = ResponseDTO(listOf(fixtVideoDTO), listOf(fixtStoryDTO))

        val fixtVideoEntity = fixture.create(VideoEntity::class.java)
        val fixtStoryEntity = fixture.create(StoryEntity::class.java)


        whenever(mockApiService.getNewsList()).thenReturn(Observable.just(fixtResponse))
        whenever(mockStoryMapper.mapFromDTO(fixtStoryDTO)).thenReturn(fixtStoryEntity)
        whenever(mockVideoMapper.mapFromDto(fixtVideoDTO)).thenReturn(fixtVideoEntity)

        sut.getFromNetwork().test()
            .assertValue(listOf(fixtVideoEntity, fixtStoryEntity))


        verify(mockApiService).getNewsList()
        verify(mockStoryMapper).mapFromDTO(fixtStoryDTO)
        verify(mockVideoMapper).mapFromDto(fixtVideoDTO)

    }

    @Test
    fun getFromNetwork_completes() {
        val fixtVideoDTO = fixture.create(VideoDTO::class.java)
        val fixtStoryDTO = fixture.create(StoryDTO::class.java)

        val fixtResponse = ResponseDTO(listOf(fixtVideoDTO), listOf(fixtStoryDTO))

        val fixtVideoEntity = fixture.create(VideoEntity::class.java)
        val fixtStoryEntity = fixture.create(StoryEntity::class.java)


        whenever(mockApiService.getNewsList()).thenReturn(Observable.just(fixtResponse))
        whenever(mockStoryMapper.mapFromDTO(fixtStoryDTO)).thenReturn(fixtStoryEntity)
        whenever(mockVideoMapper.mapFromDto(fixtVideoDTO)).thenReturn(fixtVideoEntity)

        sut.getFromNetwork().test()
            .assertComplete()
    }

}