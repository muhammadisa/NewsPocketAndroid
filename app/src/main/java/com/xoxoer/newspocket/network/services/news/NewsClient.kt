package com.xoxoer.newspocket.network.services.news

import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import io.reactivex.Single
import retrofit2.http.Query
import javax.inject.Inject

class NewsClient @Inject constructor(
    private val newsService: NewsService
) {

    fun fetchSource(
        category: String?,
        language: String?,
        country: String?
    ): Single<Sources> {
        return newsService.fetchSource(category, language, country)
    }

    fun fetchHeadline(): Single<Headlines> {
        return newsService.fetchHeadline()
    }

    fun fetchHeadlineByFilter(
        query: String?,
        category: String?,
        country: String?
    ): Single<Headlines>{
        return newsService.fetchHeadlineByFilter(query, category, country)
    }

    fun fetchHeadlineBySource(source: String): Single<Headlines> {
        return newsService.fetchHeadlineBySource(source)
    }

    fun fetchEverythingByQuery(query: String, language: String?): Single<Headlines> {
        return newsService.fetchEverythingByQuery(query, language)
    }

}