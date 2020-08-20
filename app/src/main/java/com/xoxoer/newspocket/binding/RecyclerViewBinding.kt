package com.xoxoer.newspocket.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.model.headline.Headlines
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.ui.adapters.HeadlineAdapter
import com.xoxoer.newspocket.ui.adapters.SourceAdapter

@BindingAdapter("rvLLM")
fun bindRecyclerViewLLM(view: RecyclerView, orientation: Int) {
    view.layoutManager = LinearLayoutManager(
        view.context,
        orientation,
        false
    )
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterSource")
fun binSourcedAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterHeadline")
fun bindHeadlineAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterSourceList")
fun bindAdapterSourceList(view: RecyclerView, sources: Sources?) {
    if (!sources?.sources.isNullOrEmpty()) {
        (view.adapter as? SourceAdapter)?.addSources(sources!!.sources)
    }
}

@BindingAdapter("adapterHeadlineList")
fun bindAdapterHeadlineList(view: RecyclerView, headlines: Headlines?) {
    if (!headlines?.articles.isNullOrEmpty()) {
        (view.adapter as? HeadlineAdapter)?.addHeadlines(headlines!!.articles)
    }
}