package com.xoxoer.newspocket.network.services.example

import com.xoxoer.newspocket.model.example.Example
import io.reactivex.Single
import retrofit2.http.GET

interface ExampleService{

    @GET("comments/1")
    fun fetchExample(): Single<Example>

}