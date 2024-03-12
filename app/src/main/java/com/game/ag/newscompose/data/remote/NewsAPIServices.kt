package com.game.ag.newscompose.data.remote

import com.game.ag.newscompose.domain.model.News
import com.game.ag.newscompose.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIServices {

    @GET("/v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") country:String="us",
        @Query("apiKey") apiKey:String=Constants.API_KEY
    )
}


//https://newsapi.org /v2/top-headlines ?country=us &apiKey=ece0393f10644b12bac90080fddfb6b7
