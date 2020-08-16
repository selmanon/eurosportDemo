package com.tech.demo.store.datasource

import com.flextrade.jfixture.JFixture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tech.demo.entity.StoryEntity
import com.tech.demo.entity.VideoEntity
import com.tech.demo.store.INewsNetwork
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class NewsNetworkDataSourceTest {

    @Mock
    lateinit var mockNetwork: INewsNetwork
    lateinit var sut: NewsNetworkDataSource
    lateinit var fixture: JFixture

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fixture = JFixture()
        sut = NewsNetworkDataSource(mockNetwork)
    }

    @Test
    fun getNewsList() {
        val fixtList = listOf(stubStory(), stubVideo())
        whenever(mockNetwork.getFromNetwork()).thenReturn(Observable.just(fixtList))

        sut.getNewsList().test().assertValue(fixtList)

        verify(mockNetwork).getFromNetwork()
    }

    private fun stubStory() = fixture.create(StoryEntity::class.java)

    private fun stubVideo() = fixture.create(VideoEntity::class.java)
}