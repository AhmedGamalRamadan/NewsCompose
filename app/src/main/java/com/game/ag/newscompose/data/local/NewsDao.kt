package com.game.ag.newscompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.game.ag.newscompose.domain.model.Article


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM news_table")
    suspend fun getAllNewsFavorite():List<Article>
}