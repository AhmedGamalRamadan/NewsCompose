package com.game.ag.newscompose.domain.repository


import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.model.News

interface  NewsRepo {

    suspend fun getAllNews(
        category: String
    ): News

    suspend fun getNewsByName(
        q: String
    ): News


    suspend fun insertNews(article: Article)

    suspend fun deleteNews(article: Article)

    suspend fun getAllNewsFavorite():List<Article>
}