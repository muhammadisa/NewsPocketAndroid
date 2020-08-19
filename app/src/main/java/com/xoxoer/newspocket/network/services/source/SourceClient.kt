package com.xoxoer.newspocket.network.services.source

import com.xoxoer.newspocket.model.source.Sources
import io.reactivex.Single
import javax.inject.Inject

class SourceClient @Inject constructor(
    private val sourceService: SourceService
) {

    fun fetchSource(): Single<Sources> {
        return sourceService.fetchSource()
    }

}