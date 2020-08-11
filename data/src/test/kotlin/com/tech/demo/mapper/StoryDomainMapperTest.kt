package com.tech.demo.mapper

import com.flextrade.jfixture.JFixture
import com.tech.demo.entity.StoryEntity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StoryDomainMapperTest {

    lateinit var sut: StoryDomainMapper
    lateinit var fixture:JFixture

    @Before
    fun setup() {
        sut = StoryDomainMapper()
        fixture = JFixture()
    }

    @Test
    fun mapFromEntity() {
        val fixtEntity = fixture.create(StoryEntity::class.java)

        val domain = sut.mapFromEntity(fixtEntity)

        assertEquals(fixtEntity.storyTitle, domain.storyTitle)
        assertEquals(fixtEntity.storyDate, domain.storyDate)
        assertEquals(fixtEntity.storyImage, domain.storyImage)
        assertEquals(fixtEntity.storySport, domain.storySport)
        assertEquals(fixtEntity.storyTeaser, domain.storyTeaser)
    }
}