package com.game.ag.newscompose.data.repository

import com.game.ag.newscompose.data.local.NewsDatabase
import com.game.ag.newscompose.data.remote.NewsAPIServices
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.repository.NewsRepo

class NewsRepoImpl(
    private val newsAPIServices: NewsAPIServices,
    private val newsDatabase: NewsDatabase
) : NewsRepo {

    override suspend fun getAllNews(category: String) =
        newsAPIServices.getAllNews(category = category)


    override suspend fun getNewsByName(q: String) =
        newsAPIServices.getNewsByName(q)


    override suspend fun insertNews(article: Article) {
        newsDatabase.newsDao().insertNews(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsDatabase.newsDao().deleteNews(article)
    }

    override suspend fun getAllNewsFavorite()=
        newsDatabase.newsDao().getAllNewsFavorite()
}