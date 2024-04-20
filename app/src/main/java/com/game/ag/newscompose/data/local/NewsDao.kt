package com.game.ag.newscompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articleEntity: ArticleEntity)

    @Delete
    suspend fun deleteNews(articleEntity: ArticleEntity)

    @Query("SELECT * FROM news_table")
    suspend fun getAllNewsFavorite():List<ArticleEntity>
}