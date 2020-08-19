package com.xoxoer.newspocket.network.services.news

import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import io.reactivex.Single
import javax.inject.Inject

class NewsClient @Inject constructor(
    private val newsService: NewsService
) {

    fun fetchSource(): Single<Sources> {
        return newsService.fetchSource()
    }

    fun fetchHeadline(): Single<Headlines> {
        return newsService.fetchHeadline()
    }

    fun fetchHeadlineBySource(source: String): Single<Headlines> {
        return newsService.fetchHeadlineBySource(source)
    }

    fun fetchEverythingByQuery(query: String): Single<Headlines> {
        return newsService.fetchEverythingByQuery(query)
    }

}