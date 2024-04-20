package com.game.ag.newscompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.game.ag.newscompose.domain.model.Source

@Entity(tableName = "news_table")
data class ArticleEntity(
    @PrimaryKey
    val newsId :String,

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
)