package com.xoxoer.newspocket.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.xoxoer.newspocket.RxTrampolineSchedulerRule
import com.xoxoer.newspocket.model.example.Example
import com.xoxoer.newspocket.repository.example.ExampleRepository
import com.xoxoer.newspocket.ui.viewmodels.ExampleViewModel
import com.xoxoer.newspocket.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleViewModelTest {

    private lateinit var viewModel: ExampleViewModel
    private lateinit var exampleRepository: ExampleRepository

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        exampleRepository = mock()
        viewModel = mock()
    }

    @Test
    fun fetchExampleTest() = runBlocking {
        val mockData = MockUtil.mockExample()

        val observer: Observer<Example> = mock()
        val fetchedData = MutableLiveData<Example>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchExample())
            .then {
                verify(viewModel, atLeastOnce()).fetchExample()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

}