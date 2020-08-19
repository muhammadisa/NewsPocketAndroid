package com.xoxoer.newspocket.network.services.source

import com.xoxoer.newspocket.model.source.Sources
import io.reactivex.Single
import retrofit2.http.GET

interface SourceService {

    @GET("sources/")
    fun fetchSource(): Single<Sources>

}