package com.xoxoer.newspocket.di

import com.xoxoer.newspocket.annotations.BasicRetrofitAuthenticatedClient
import com.xoxoer.newspocket.annotations.BasicRetrofitClient
import com.xoxoer.newspocket.network.services.example.ExampleClient
import com.xoxoer.newspocket.network.services.example.ExampleService
import com.xoxoer.newspocket.network.services.news.NewsClient
import com.xoxoer.newspocket.network.services.news.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesExampleService(
        @BasicRetrofitClient retrofit: Retrofit
    ): ExampleService {
        return retrofit.create(ExampleService::class.java)
    }

    @Provides
    @Singleton
    fun providesExampleClient(exampleService: ExampleService): ExampleClient {
        return ExampleClient(
            exampleService
        )
    }

    @Provides
    @Singleton
    fun providesNewsService(
        @BasicRetrofitAuthenticatedClient retrofit: Retrofit
    ): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsClient(newsService: NewsService): NewsClient {
        return NewsClient(
            newsService
        )
    }

}