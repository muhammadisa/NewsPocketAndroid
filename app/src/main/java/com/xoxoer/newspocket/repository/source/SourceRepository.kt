package com.xoxoer.newspocket.repository.source

import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.network.services.source.SourceClient
import com.xoxoer.newspocket.repository.Repository
import com.xoxoer.newspocket.utils.rx.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SourceRepository @Inject constructor(
    private val sourceClient: SourceClient
) : Repository {

    fun fetchSource(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<Sources>
    ) {
        sourceClient.fetchSource()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

}