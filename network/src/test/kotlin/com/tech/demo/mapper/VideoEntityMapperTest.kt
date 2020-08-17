package com.tech.demo.mapper

import com.flextrade.jfixture.JFixture
import com.tech.demo.dto.VideoDTO
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VideoEntityMapperTest {

    lateinit var sut:VideoEntityMapper
    lateinit var fixture:JFixture

    @Before
    fun setUp() {
        sut = VideoEntityMapper()
        fixture = JFixture()
    }

    @Test
    fun mapFromDto() {

        val fixtDto = fixture.create(VideoDTO::class.java)
        
        val entity = sut.mapFromDto(fixtDto)

        assertEquals(fixtDto.id, entity.id)
        assertEquals(fixtDto.videoTitle, entity.videoTitle)
        assertEquals(fixtDto.videoImage, entity.videoImage)
        assertEquals(fixtDto.videoDate.toLong(), entity.videoDate)
        assertEquals(fixtDto.videoSport.SportName, entity.videoSport)
        assertEquals(fixtDto.videoUrl, entity.videoUrl)
        assertEquals(fixtDto.videoViews, entity.videoViews)

    }
}