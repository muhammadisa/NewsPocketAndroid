package com.xoxoer.newspocket.network.services.example

import com.xoxoer.newspocket.model.example.Example
import io.reactivex.Single
import javax.inject.Inject

class ExampleClient @Inject constructor(
    private val exampleService: ExampleService
) {

    fun fetchExample(): Single<Example> {
        return exampleService.fetchExample()
    }

}