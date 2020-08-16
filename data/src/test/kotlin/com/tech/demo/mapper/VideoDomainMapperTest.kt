package com.tech.demo.mapper

import com.flextrade.jfixture.JFixture
import com.tech.demo.entity.VideoEntity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VideoDomainMapperTest {

    lateinit var sut: VideoDomainMapper
    lateinit var fixture:JFixture

    @Before
    fun setup() {
        sut = VideoDomainMapper()
        fixture = JFixture()
    }

    @Test
    fun mapFromEntity() {
        val fixtEntity = fixture.create(VideoEntity::class.java)

        val domain = sut.mapFromEntity(fixtEntity)

        assertEquals(fixtEntity.videoTitle, domain.videoTitle)
        assertEquals(fixtEntity.videoDate, domain.videoDate)
        assertEquals(fixtEntity.videoImage, domain.videoImage)
        assertEquals(fixtEntity.videoSport, domain.videoSport)
        assertEquals(fixtEntity.videoUrl, domain.videoUrl)
        assertEquals(fixtEntity.videoViews, domain.videoViews)
    }
}