package com.xoxoer.newspocket.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.model.source.Sources
import com.xoxoer.newspocket.ui.adapters.SourceAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("adapterSourceList")
fun bindAdapterTodoList(view: RecyclerView, sources: Sources?){
    if(!sources?.sources.isNullOrEmpty()){
        (view.adapter as? SourceAdapter)?.addSources(sources!!.sources)
    }
}