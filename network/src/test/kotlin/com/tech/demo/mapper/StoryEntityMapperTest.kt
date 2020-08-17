package com.tech.demo.mapper

import com.flextrade.jfixture.JFixture
import com.tech.demo.dto.StoryDTO
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StoryEntityMapperTest {


    lateinit var sut: StoryEntityMapper

    lateinit var fixture: JFixture

    @Before
    fun setup() {
        fixture = JFixture()
        sut = StoryEntityMapper()
    }


    @Test
    fun testMap() {
        val fixtDto = fixture.create(StoryDTO::class.java)

        val entity = sut.mapFromDTO(fixtDto)

        assertEquals(fixtDto.id, entity.id)
        assertEquals(fixtDto.storyTitle, entity.storyTitle)
        assertEquals(fixtDto.storyTeaser, entity.storyTeaser)
        assertEquals(fixtDto.storyImage, entity.storyImage)
        assertEquals(fixtDto.storyDate.toLong(), entity.storyDate)
        assertEquals(fixtDto.storySport.SportName, entity.storySport)
        assertEquals(fixtDto.storyAuthor, entity.storyAuthor)

    }
}