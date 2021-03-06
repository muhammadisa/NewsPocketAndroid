package com.xoxoer.newspocket.di

import android.content.Context
import com.xoxoer.newspocket.network.HeaderInterceptor
import com.xoxoer.newspocket.network.HttpRequestInterceptor
import com.xoxoer.newspocket.BuildConfig
import com.xoxoer.newspocket.annotations.BasicOkHttpAuthenticatedClient
import com.xoxoer.newspocket.annotations.BasicOkHttpClient
import com.xoxoer.newspocket.annotations.BasicRetrofitAuthenticatedClient
import com.xoxoer.newspocket.annotations.BasicRetrofitClient
import com.xoxoer.lifemarklibrary.Lifemark
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLifemark(@ApplicationContext context: Context): Lifemark {
        return Lifemark(context)
    }

    @Provides
    @BasicOkHttpClient
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                when (BuildConfig.DEBUG) {
                    true -> setLevel(HttpLoggingInterceptor.Level.BODY)
                    false -> setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            }).build()
    }

    @Provides
    @BasicOkHttpAuthenticatedClient
    fun provideOkHttpClientAuthenticated(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .addInterceptor(HeaderInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor().apply {
                when (BuildConfig.DEBUG) {
                    true -> setLevel(HttpLoggingInterceptor.Level.BODY)
                    false -> setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            }).build()
    }

    @Provides
    @BasicRetrofitClient
    fun provideRetrofit(
        @BasicOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .build()
    }

    @Provides
    @BasicRetrofitAuthenticatedClient
    fun provideRetrofitAuthenticated(
        @BasicOkHttpAuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .baseUrl("https://newsapi.org/v2/")
            .build()
    }

}