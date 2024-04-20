package com.game.ag.newscompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.game.ag.newscompose.data.converter.Converters

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase :RoomDatabase(){

    abstract fun newsDao():NewsDao
}