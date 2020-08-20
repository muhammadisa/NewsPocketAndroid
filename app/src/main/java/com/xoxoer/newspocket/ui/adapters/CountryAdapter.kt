package com.xoxoer.newspocket.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.databinding.CardViewCountryBinding
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

class CountryAdapter constructor(private val newsViewModel: NewsViewModel)
    : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val items: MutableList<String> = mutableListOf(
        "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch",
        "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr",
        "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr",
        "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz",
        "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg",
        "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryAdapter.CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewCountryBinding>(
                inflater,
                R.layout.card_view_country,
                parent,
                false
            )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            value = item
            executePendingBindings()
            cardViewCountry.setOnClickListener {
                newsViewModel.country.set(item)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class CountryViewHolder(val binding: CardViewCountryBinding) :
        RecyclerView.ViewHolder(binding.root)

}