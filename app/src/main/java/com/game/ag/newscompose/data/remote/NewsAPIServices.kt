package com.game.ag.newscompose.data.remote

import com.game.ag.newscompose.domain.model.News
import com.game.ag.newscompose.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIServices {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country:String="us",
        @Query("category") category:String,
        @Query("apiKey") apiKey:String=Constants.API_KEY
    ):News
}

//https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=ece0393f10644b12bac90080fddfb6b7
