package com.xoxoer.newspocket.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.databinding.CardViewLanguageBinding
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

class LanguageAdapter constructor(private val newsViewModel: NewsViewModel)
    : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    private val items: MutableList<String> = mutableListOf(
        "ar", "de", "en", "es", "fr", "he", "it",
        "nl", "no", "pt", "ru", "se", "ud", "zh"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageAdapter.LanguageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewLanguageBinding>(
                inflater,
                R.layout.card_view_language,
                parent,
                false
            )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageAdapter.LanguageViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            value = item
            executePendingBindings()
            cardViewLanguage.setOnClickListener {
                newsViewModel.language.set(item)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class LanguageViewHolder(val binding: CardViewLanguageBinding) :
        RecyclerView.ViewHolder(binding.root)

}