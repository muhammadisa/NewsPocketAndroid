package com.xoxoer.newspocket.network.services.headline

data class Headlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)