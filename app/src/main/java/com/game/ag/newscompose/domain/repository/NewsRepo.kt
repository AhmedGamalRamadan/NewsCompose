package com.game.ag.newscompose.domain.repository

import com.game.ag.newscompose.domain.model.News
import com.game.ag.newscompose.util.Constants

interface NewsRepo {

    suspend fun getAllNews(
        country: String = "us",
        category:String,
        apiKey: String = Constants.API_KEY
    ):News
}