package com.game.ag.newscompose.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.ag.newscompose.data.repository.NewsRepoImpl
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.model.News
import com.game.ag.newscompose.domain.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {

    init {
        getTopHeadMovies()
    }

    private val _topHeadMovies = MutableStateFlow<List<Article>>(emptyList())
    val topHeadMovie = _topHeadMovies.asStateFlow()


     private fun getTopHeadMovies() {
        viewModelScope.launch {
            try {
                val result = newsRepo.getAllNews()
                    _topHeadMovies.value = result.articles

            } catch (e: IOException) {
                Log.d("NewsViewModel", e.message.toString())
            }


        }
    }
}








