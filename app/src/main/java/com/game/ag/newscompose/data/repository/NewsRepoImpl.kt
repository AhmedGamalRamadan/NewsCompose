package com.game.ag.newscompose.data.repository

import com.game.ag.newscompose.data.remote.NewsAPIServices
import com.game.ag.newscompose.domain.repository.NewsRepo

class NewsRepoImpl(private val newsAPIServices: NewsAPIServices) : NewsRepo {

    override suspend fun getAllNews(country: String, apiKey: String) =
        newsAPIServices.getAllNews(country, apiKey)


}