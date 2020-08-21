package com.xoxoer.newspocket.network

import com.nhaarman.mockitokotlin2.mock
import com.xoxoer.newspocket.RxTrampolineSchedulerRule
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.network.services.news.NewsClient
import com.xoxoer.newspocket.network.services.news.NewsService
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class NewsServiceTest : ApiAbstract<NewsService>() {

    private lateinit var service: NewsService
    private val client: NewsClient = mock()

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun initService() {
        service = createService(NewsService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchSourceFromNetworkTest() = runBlocking {
        enqueueResponse("/Source.json")
        val response = service.fetchSource(
            "", "", ""
        ).subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        val responseBody = requireNotNull((response as Sources))
        mockWebServer.takeRequest()
        client.fetchSource("", "", "")
        assertThat(responseBody.status, `is`("ok"))
        responseBody.sources.forEach {
            assertThat(it.id, `is`("source_id"))
            assertThat(it.name, `is`("source_name"))
            assertThat(it.description, `is`("source_description"))
            assertThat(it.url, `is`("https://abcnews.go.com"))
            assertThat(it.category, `is`("general"))
            assertThat(it.language, `is`("en"))
            assertThat(it.country, `is`("us"))
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchHeadlineFromNetworkTest() = runBlocking {
        enqueueResponse("/Headline.json")
        val response = service.fetchHeadline().subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        val responseBody = requireNotNull((response as Headlines))
        mockWebServer.takeRequest()
        client.fetchHeadline()
        assertThat(responseBody.status, `is`("ok"))
        assertThat(responseBody.totalResults, `is`(38))
        responseBody.articles.forEach {
            assertThat(it.source?.id, `is`("article_source_id"))
            assertThat(it.source?.name, `is`("article_source_name"))
            assertThat(it.description, `is`("article_description"))
            assertThat(it.url, `is`("https://www.bbc.com/news/world-europe-53865811"))
            assertThat(
                it.urlToImage,
                `is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
            )
            assertThat(it.publishedAt, `is`("2020-08-21T16:18:45Z"))
            assertThat(it.content, `is`("article_content"))
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchEverythingFromNetworkTest() = runBlocking {
        enqueueResponse("/Headline.json")
        val response = service.fetchEverythingByQuery("", "")
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        val responseBody = requireNotNull((response as Headlines))
        mockWebServer.takeRequest()
        client.fetchEverythingByQuery("", "")
        assertThat(responseBody.status, `is`("ok"))
        assertThat(responseBody.totalResults, `is`(38))
        responseBody.articles.forEach {
            assertThat(it.source?.id, `is`("article_source_id"))
            assertThat(it.source?.name, `is`("article_source_name"))
            assertThat(it.description, `is`("article_description"))
            assertThat(it.url, `is`("https://www.bbc.com/news/world-europe-53865811"))
            assertThat(
                it.urlToImage,
                `is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
            )
            assertThat(it.publishedAt, `is`("2020-08-21T16:18:45Z"))
            assertThat(it.content, `is`("article_content"))
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchHeadlineByFilterFromNetworkTest() = runBlocking {
        enqueueResponse("/Headline.json")
        val response = service.fetchHeadlineByFilter("", "", "")
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        val responseBody = requireNotNull((response as Headlines))
        mockWebServer.takeRequest()
        client.fetchHeadlineByFilter("", "", "")
        assertThat(responseBody.status, `is`("ok"))
        assertThat(responseBody.totalResults, `is`(38))
        responseBody.articles.forEach {
            assertThat(it.source?.id, `is`("article_source_id"))
            assertThat(it.source?.name, `is`("article_source_name"))
            assertThat(it.description, `is`("article_description"))
            assertThat(it.url, `is`("https://www.bbc.com/news/world-europe-53865811"))
            assertThat(
                it.urlToImage,
                `is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
            )
            assertThat(it.publishedAt, `is`("2020-08-21T16:18:45Z"))
            assertThat(it.content, `is`("article_content"))
        }
    }

    @Throws(IOException::class)
    @Test
    fun fetchHeadlineBySourceFromNetworkTest() = runBlocking {
        enqueueResponse("/Headline.json")
        val response = service.fetchHeadlineBySource("")
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        val responseBody = requireNotNull((response as Headlines))
        mockWebServer.takeRequest()
        client.fetchHeadlineBySource("")
        assertThat(responseBody.status, `is`("ok"))
        assertThat(responseBody.totalResults, `is`(38))
        responseBody.articles.forEach {
            assertThat(it.source?.id, `is`("article_source_id"))
            assertThat(it.source?.name, `is`("article_source_name"))
            assertThat(it.description, `is`("article_description"))
            assertThat(it.url, `is`("https://www.bbc.com/news/world-europe-53865811"))
            assertThat(
                it.urlToImage,
                `is`("https://ichef.bbci.co.uk/news/1024/branded_news/5B9D/production/_114035432_mediaitem114035431.jpg")
            )
            assertThat(it.publishedAt, `is`("2020-08-21T16:18:45Z"))
            assertThat(it.content, `is`("article_content"))
        }
    }


}