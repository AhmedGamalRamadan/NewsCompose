package com.game.ag.newscompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String?,

    var isFavorite: Boolean = false
)