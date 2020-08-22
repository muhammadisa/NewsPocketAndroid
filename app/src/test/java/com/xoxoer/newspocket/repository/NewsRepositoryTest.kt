package com.xoxoer.newspocket.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.xoxoer.newspocket.RxTrampolineSchedulerRule
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.network.services.news.NewsClient
import com.xoxoer.newspocket.network.services.news.NewsService
import com.xoxoer.newspocket.repository.news.NewsRepository
import com.xoxoer.newspocket.utils.MockUtil
import com.xoxoer.newspocket.utils.rx.ApiSingleObserver
import com.xoxoer.newspocket.utils.rx.Error
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsRepositoryTest {

    private lateinit var repository: NewsRepository
    private lateinit var client: NewsClient
    private lateinit var isLoading: MutableLiveData<Boolean>
    private val service: NewsService = mock()

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        isLoading = MutableLiveData(false)
        client = NewsClient(service)
        repository = NewsRepository(client)
    }

    @Test
    fun fetchSourceFromNetworkTest() = runBlocking {
        whenever(service.fetchSource("", "", ""))
            .thenReturn(Single.just(MockUtil.mockSource()))

        repository.fetchSource("", "", "", {}, {},
            object : ApiSingleObserver<Sources>(CompositeDisposable()) {
                override fun onResult(data: Sources) {
                    assertThat(data.status, Is.`is`("ok"))
                    data.sources.forEach {
                        assertThat(it.id, Is.`is`("source_id"))
                        assertThat(it.name, Is.`is`("source_name"))
                        assertThat(it.description, Is.`is`("source_description"))
                        assertThat(it.url, Is.`is`("https://abcnews.go.com"))
                        assertThat(it.category, Is.`is`("general"))
                        assertThat(it.language, Is.`is`("en"))
                        assertThat(it.country, Is.`is`("us"))
                    }
                }

                override fun onError(e: Error) {}
            })
    }

    @Test
    fun fetchHeadlineFromNetworkTest() = runBlocking {
        whenever(service.fetchHeadline())
            .thenReturn(Single.just(MockUtil.mockHeadline()))

        repository.fetchHeadline({}, {},
            object : ApiSingleObserver<Headlines>(CompositeDisposable()) {
                override fun onResult(data: Headlines) {
                    assertThat(data.status, Is.`is`("ok"))
                    assertThat(data.totalResults, Is.`is`(38))
                    data.articles.forEach {
                        assertThat(it.source?.id, Is.`is`("article_source_id"))
                        assertThat(it.source?.name, Is.`is`("article_source_name"))
                        assertThat(it.description, Is.`is`("article_description"))
                        assertThat(
                            it.url,
                            Is.`is`("https://www.bbc.com/news/world-europe-53865811")
                        )
                        assertThat(
                            it.urlToImage,
                            Is.`is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
                        )
                        assertThat(it.publishedAt, Is.`is`("2020-08-21T16:18:45Z"))
                        assertThat(it.content, Is.`is`("article_content"))
                    }
                }

                override fun onError(e: Error) {}
            })
    }

    @Test
    fun fetchEverythingFromNetworkTest() = runBlocking {
        whenever(service.fetchEverythingByQuery("", ""))
            .thenReturn(Single.just(MockUtil.mockHeadline()))

        repository.fetchEverythingByQuery("", "", {}, {},
            object : ApiSingleObserver<Headlines>(CompositeDisposable()) {
                override fun onResult(data: Headlines) {
                    assertThat(data.status, Is.`is`("ok"))
                    assertThat(data.totalResults, Is.`is`(38))
                    data.articles.forEach {
                        assertThat(it.source?.id, Is.`is`("article_source_id"))
                        assertThat(it.source?.name, Is.`is`("article_source_name"))
                        assertThat(it.description, Is.`is`("article_description"))
                        assertThat(
                            it.url,
                            Is.`is`("https://www.bbc.com/news/world-europe-53865811")
                        )
                        assertThat(
                            it.urlToImage,
                            Is.`is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
                        )
                        assertThat(it.publishedAt, Is.`is`("2020-08-21T16:18:45Z"))
                        assertThat(it.content, Is.`is`("article_content"))
                    }
                }

                override fun onError(e: Error) {}
            })
    }

    @Test
    fun fetchHeadlineByFilterFromNetworkTest() = runBlocking {
        whenever(service.fetchHeadlineByFilter("", "", ""))
            .thenReturn(Single.just(MockUtil.mockHeadline()))

        repository.fetchHeadlineByFilter("", "", "", {}, {},
            object : ApiSingleObserver<Headlines>(CompositeDisposable()) {
                override fun onResult(data: Headlines) {
                    assertThat(data.status, Is.`is`("ok"))
                    assertThat(data.totalResults, Is.`is`(38))
                    data.articles.forEach {
                        assertThat(it.source?.id, Is.`is`("article_source_id"))
                        assertThat(it.source?.name, Is.`is`("article_source_name"))
                        assertThat(it.description, Is.`is`("article_description"))
                        assertThat(
                            it.url,
                            Is.`is`("https://www.bbc.com/news/world-europe-53865811")
                        )
                        assertThat(
                            it.urlToImage,
                            Is.`is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
                        )
                        assertThat(it.publishedAt, Is.`is`("2020-08-21T16:18:45Z"))
                        assertThat(it.content, Is.`is`("article_content"))
                    }
                }

                override fun onError(e: Error) {}
            })
    }

    @Test
    fun fetchHeadlineBySourceFromNetworkTest() = runBlocking {
        whenever(service.fetchHeadlineBySource(""))
            .thenReturn(Single.just(MockUtil.mockHeadline()))

        repository.fetchHeadlineBySource("", {}, {},
            object : ApiSingleObserver<Headlines>(CompositeDisposable()) {
                override fun onResult(data: Headlines) {
                    assertThat(data.status, Is.`is`("ok"))
                    assertThat(data.totalResults, Is.`is`(38))
                    data.articles.forEach {
                        assertThat(it.source?.id, Is.`is`("article_source_id"))
                        assertThat(it.source?.name, Is.`is`("article_source_name"))
                        assertThat(it.description, Is.`is`("article_description"))
                        assertThat(
                            it.url,
                            Is.`is`("https://www.bbc.com/news/world-europe-53865811")
                        )
                        assertThat(
                            it.urlToImage,
                            Is.`is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
                        )
                        assertThat(it.publishedAt, Is.`is`("2020-08-21T16:18:45Z"))
                        assertThat(it.content, Is.`is`("article_content"))
                    }
                }

                override fun onError(e: Error) {}
            })
    }

}