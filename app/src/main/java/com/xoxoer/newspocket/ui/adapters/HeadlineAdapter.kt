package com.xoxoer.newspocket.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.databinding.CardViewHeadlinesBinding
import com.xoxoer.newspocket.extensions.load
import com.xoxoer.newspocket.model.headline.Article
import com.xoxoer.newspocket.route.InitialRouteName.NEWS_VIEWER
import com.xoxoer.newspocket.route.Route

class HeadlineAdapter : RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>() {

    private val items: MutableList<Article> = mutableListOf()

    fun addHeadlines(headlines: List<Article>) {
        items.addAll(headlines)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadlineAdapter.HeadlineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewHeadlinesBinding>(
                inflater,
                R.layout.card_view_headlines,
                parent,
                false
            )
        return HeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineAdapter.HeadlineViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            headline = item
            item.urlToImage.let {
                if (it.isNullOrBlank()) {
                    imageHeadline.load("https://via.placeholder.com/150")
                } else {
                    imageHeadline.load(it)
                }
            }
            executePendingBindings()
            cardViewHeadline.setOnClickListener {
                Route(it.context, NEWS_VIEWER).navigate("ARTICLE", item)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class HeadlineViewHolder(val binding: CardViewHeadlinesBinding) :
        RecyclerView.ViewHolder(binding.root)

}