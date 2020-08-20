package com.xoxoer.newspocket.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.newspocket.R
import com.xoxoer.newspocket.databinding.CardViewCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val items: MutableList<String> = mutableListOf(
        "entertainment",
        "general",
        "health",
        "science",
        "sports",
        "technology"
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewCategoryBinding>(
                inflater,
                R.layout.card_view_category,
                parent,
                false
            )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            value = item
            executePendingBindings()
            cardViewCategory.setOnClickListener {}
        }
    }

    override fun getItemCount() = items.size

    inner class CategoryViewHolder(val binding: CardViewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

}