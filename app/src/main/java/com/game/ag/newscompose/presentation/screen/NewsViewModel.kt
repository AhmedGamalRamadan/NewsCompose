package com.game.ag.newscompose.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.repository.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : ViewModel() {

    init {
        getNewsByCategory("General")
    }

    private val _allNewsResponse = MutableStateFlow<List<Article>>(emptyList())
    val allNewsResponse = _allNewsResponse.asStateFlow()

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val newsResult = newsRepo.getAllNews(category = category)
                _allNewsResponse.value = newsResult.articles
            } catch (e: Exception) {
                Log.d("viewModel", "Error getting news by category: ${e.message.toString()}")
            }
        }
    }

    fun getNewsByName(newsName: String) {
        viewModelScope.launch {
            try {
                _allNewsResponse.value = newsRepo.getNewsByName(newsName).articles

            } catch (e: Exception) {
                Log.d("viewModel", "Error getting news by name: ${e.message.toString()}")
            }
        }
    }

    /*  Deals with database */

    fun getFavoriteNews() {
        viewModelScope.launch {
            try {
                _allNewsResponse.value = newsRepo.getAllNewsFavorite()

            } catch (e: Exception) {

                Log.d("viewModel", "Error getting favorite news: ${e.message.toString()}")
            }
        }
    }

    fun toggleFavorite(article: Article) {

        article.isFavorite = !article.isFavorite

        viewModelScope.launch {
            try {
                if (article.isFavorite) {
                    newsRepo.insertNews(article)
                } else {
                    newsRepo.deleteNews(article)
                }
            } catch (e: Exception) {
                Log.d("viewModel", "Error getting toggling favorite news: ${e.message.toString()}")

            }
        }
    }
}








