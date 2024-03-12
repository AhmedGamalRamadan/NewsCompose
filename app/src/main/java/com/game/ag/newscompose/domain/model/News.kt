package com.game.ag.newscompose.domain.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

