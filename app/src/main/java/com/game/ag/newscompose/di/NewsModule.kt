package com.game.ag.newscompose.di


import android.app.Application
import androidx.room.Room
import com.game.ag.newscompose.data.local.NewsDatabase
import com.game.ag.newscompose.data.remote.NewsAPIServices
import com.game.ag.newscompose.data.repository.NewsRepoImpl
import com.game.ag.newscompose.domain.repository.NewsRepo
import com.game.ag.newscompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(20, TimeUnit.MILLISECONDS)
            .readTimeout(20, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): NewsAPIServices {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIServices::class.java)
    }

    @Provides
    @Singleton
    fun providesRepo(newsAPIServices: NewsAPIServices,newsDatabase: NewsDatabase): NewsRepo {
        return NewsRepoImpl(newsAPIServices,newsDatabase)
    }


    @Provides
    @Singleton
    fun providesNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

}

















