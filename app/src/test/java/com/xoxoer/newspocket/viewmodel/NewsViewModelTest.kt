package com.xoxoer.newspocket.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.xoxoer.newspocket.RxTrampolineSchedulerRule
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.repository.news.NewsRepository
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel
import com.xoxoer.newspocket.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsRepository: NewsRepository

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        newsRepository = mock()
        viewModel = mock()
    }

    @Test
    fun fetchSourceTest() = runBlocking {
        val mockData = MockUtil.mockSource()

        val observer: Observer<Sources> = mock()
        val fetchedData = MutableLiveData<Sources>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchSource())
            .then {
                verify(viewModel, atLeastOnce()).fetchSource()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

    @Test
    fun fetchHeadlineTest() = runBlocking {
        val mockData = MockUtil.mockHeadline()

        val observer: Observer<Headlines> = mock()
        val fetchedData = MutableLiveData<Headlines>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchHeadline())
            .then {
                verify(viewModel, atLeastOnce()).fetchHeadline()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

    @Test
    fun fetchEverythingTest() = runBlocking {
        val mockData = MockUtil.mockHeadline()

        val observer: Observer<Headlines> = mock()
        val fetchedData = MutableLiveData<Headlines>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchEverythingByQuery())
            .then {
                verify(viewModel, atLeastOnce()).fetchEverythingByQuery()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

    @Test
    fun fetchHeadlineByFilterTest() = runBlocking {
        val mockData = MockUtil.mockHeadline()

        val observer: Observer<Headlines> = mock()
        val fetchedData = MutableLiveData<Headlines>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchHeadlineByFilter())
            .then {
                verify(viewModel, atLeastOnce()).fetchHeadlineByFilter()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

    @Test
    fun fetchHeadlineBySourceTest() = runBlocking {
        val mockData = MockUtil.mockHeadline()

        val observer: Observer<Headlines> = mock()
        val fetchedData = MutableLiveData<Headlines>()
        fetchedData.postValue(mockData)
        fetchedData.observeForever(observer)

        whenever(viewModel.fetchHeadlineBySource())
            .then {
                verify(viewModel, atLeastOnce()).fetchHeadlineBySource()
            }

        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }

}