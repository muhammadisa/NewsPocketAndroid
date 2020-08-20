package com.xoxoer.newspocket.ui.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.xoxoer.lifemarklibrary.Lifemark
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.repository.news.NewsRepository
import com.xoxoer.newspocket.utils.rx.ApiSingleObserver
import com.xoxoer.newspocket.utils.rx.Error
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.filter
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository,
    private val lifemark: Lifemark,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), ViewModelContracts {

    override var valid = ObservableBoolean()
    override val isLoading = MutableLiveData(false)
    override val error = ObservableField<Boolean>()
    override val errorReason = ObservableField<String>()

    var source = ObservableField<Source>()
    var category = ObservableField<String>()
    var language = ObservableField<String>()
    var country = ObservableField<String>()
    var filterMode = ObservableField<Boolean>()

    fun clearSourceFilter(){
        category.set(null)
        language.set(null)
        country.set(null)
    }

    private val _sourceSuccess = MutableLiveData<Sources>()
    val sourceSuccess: LiveData<Sources>
        get() = _sourceSuccess

    private val _headlineSuccess = MutableLiveData<Headlines>()
    val headlineSuccess: LiveData<Headlines>
        get() = _headlineSuccess

    private fun <T> errorDispatcher(
        errorReason: String,
        targetMutable: MutableLiveData<T>
    ) {
        this.error.set(true)
        this.errorReason.set(errorReason)
        targetMutable.value = null
    }

    private fun onStart() {
        isLoading.value = true
    }

    private fun onFinish() {
        isLoading.value = false
    }

    private fun <T> handler(targetMutable: MutableLiveData<T>) =
        object : ApiSingleObserver<T>(CompositeDisposable()) {
            override fun onResult(data: T) {
                targetMutable.value = data
            }

            override fun onError(e: Error) {
                when (lifemark.isNetworkConnected()) {
                    true -> errorDispatcher(
                        e.message,
                        targetMutable
                    )
                    false -> errorDispatcher(
                        "No Internet Connection",
                        targetMutable
                    )
                }
            }
        }

    fun fetchSource() {
        newsRepository.fetchSource(
            category.get(),
            language.get(),
            country.get(),
            { onStart() },
            { onFinish() },
            handler(_sourceSuccess)
        )
    }

    fun fetchHeadline() {
        newsRepository.fetchHeadline(
            { onStart() },
            { fetchSource() },
            handler(_headlineSuccess)
        )
    }

    fun fetchHeadlineByFilter() {
        newsRepository.fetchHeadlineByFilter(
            category.get(),
            country.get(),
            { onStart() },
            { onFinish() },
            handler(_headlineSuccess)
        )
    }

    fun fetchHeadlineBySource() {
        newsRepository.fetchHeadlineBySource(
            source.get()!!.id,
            { onStart() },
            { onFinish() },
            handler(_headlineSuccess)
        )
    }

    fun fetchEverythingByQuery() {
        newsRepository.fetchEverythingByQuery(
            source.get()!!.description,
            language.get(),
            { onStart() },
            { onFinish() },
            handler(_headlineSuccess)
        )
    }

}