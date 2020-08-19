package com.xoxoer.newspocket.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.xoxoer.newspocket.RxTrampolineSchedulerRule
import com.xoxoer.newspocket.model.example.Example
import com.xoxoer.newspocket.network.services.example.ExampleClient
import com.xoxoer.newspocket.network.services.example.ExampleService
import com.xoxoer.newspocket.persistence.ExampleDao
import com.xoxoer.newspocket.repository.example.ExampleRepository
import com.xoxoer.newspocket.utils.MockUtil
import com.xoxoer.newspocket.utils.rx.ApiSingleObserver
import com.xoxoer.newspocket.utils.rx.Error
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleRepositoryTest {

    private lateinit var repository: ExampleRepository
    private lateinit var client: ExampleClient
    private lateinit var isLoading: MutableLiveData<Boolean>
    private val service: ExampleService = mock()
    private val exampleDao: ExampleDao = mock()

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        isLoading = MutableLiveData(false)
        client =
            ExampleClient(service)
        repository = ExampleRepository(
            client,
            exampleDao
        )
    }

    @Test
    fun fetchExampleFromNetworkTest() = runBlocking {
        whenever(exampleDao.getExample(id_ = 0)).thenReturn(null)
        whenever(service.fetchExample()).thenReturn(Single.just(MockUtil.mockExample()))

        repository.fetchExample(
            {},
            {},
            object : ApiSingleObserver<Example>(CompositeDisposable()) {
                override fun onResult(data: Example) {
                    assertThat(data.id, `is`(1))
                    assertThat(data.body, `is`("example_body"))
                    assertThat(data.email, `is`("example@example.com"))
                    assertThat(data.name, `is`("example_name"))
                    assertThat(data.postId, `is`(2))
                }

                override fun onError(e: Error) {}
            }
        )
    }

}