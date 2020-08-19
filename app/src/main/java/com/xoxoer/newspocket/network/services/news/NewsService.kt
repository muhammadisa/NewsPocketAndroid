package com.xoxoer.newspocket.network.services.news

import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("sources")
    fun fetchSource(): Single<Sources>

    @GET("top-headlines?country=us")
    fun fetchHeadline(): Single<Headlines>

    @GET("top-headlines")
    fun fetchHeadlineBySource(
        @Query("sources") source: String
    ): Single<Headlines>

    @GET("everything")
    fun fetchEverythingByQuery(
        @Query("q") query: String
    ): Single<Headlines>

}