package com.xoxoer.newspocket.repository.news

import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.network.services.news.NewsClient
import com.xoxoer.newspocket.repository.Repository
import com.xoxoer.newspocket.utils.rx.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsClient: NewsClient
) : Repository {

    fun fetchSource(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Sources>
    ) {
        newsClient.fetchSource()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

    fun fetchHeadline(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Headlines>
    ) {
        newsClient.fetchHeadline()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

    fun fetchHeadlineBySource(
        source: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Headlines>
    ) {
        newsClient.fetchHeadlineBySource(source)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

    fun fetchEverythingByQuery(
        query: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Headlines>
    ) {
        newsClient.fetchEverythingByQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

}