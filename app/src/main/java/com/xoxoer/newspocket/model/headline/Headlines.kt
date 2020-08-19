package com.xoxoer.newspocket.model.headline

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)