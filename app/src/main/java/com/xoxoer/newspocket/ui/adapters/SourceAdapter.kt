package com.xoxoer.newspocket.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.databinding.CardViewSourceBinding
import com.xoxoer.newspocket.model.source.Source
import com.xoxoer.newspocket.route.InitialRouteName.HEADLINE
import com.xoxoer.newspocket.route.Route
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private val items: MutableList<Source> = mutableListOf()

    fun addSources(source: List<Source>) {
        items.clear()
        items.addAll(source)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewSourceBinding>(
                inflater,
                R.layout.card_view_source,
                parent,
                false
            )
        return SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            source = item
            executePendingBindings()
            cardViewSource.setOnClickListener {
                Route(it.context, HEADLINE).navigate("SOURCE", item)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class SourceViewHolder(val binding: CardViewSourceBinding) :
        RecyclerView.ViewHolder(binding.root)

}