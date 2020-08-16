package com.tech.demo.interactor

import com.flextrade.jfixture.JFixture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tech.demo.domain.StoryDomain
import com.tech.demo.domain.VideoDomain
import com.tech.demo.repository.INewsRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsInteractorTest {

    lateinit var sut: NewsInteractor

    @Mock
    lateinit var mockRepository: INewsRepository

    lateinit var fixture: JFixture


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = NewsInteractor(mockRepository)
        fixture = JFixture()
    }

    @Test
    fun getNewsList() {
        val fixtList = listOf(stubStory(), stubVideo())
        whenever(mockRepository.getNews()).thenReturn(Observable.just(fixtList))

        sut.getNewsList().test().assertValue(fixtList)
        verify(mockRepository).getNews()

    }

    private fun stubStory() = fixture.create(StoryDomain::class.java)

    private fun stubVideo() = fixture.create(VideoDomain::class.java)

}