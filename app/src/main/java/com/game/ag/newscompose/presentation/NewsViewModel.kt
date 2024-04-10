package com.game.ag.newscompose.presentation

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
        getNews("General")
    }

    private val _allNewsResponse = MutableStateFlow<List<Article>>(emptyList())
    val allNewsResponse = _allNewsResponse.asStateFlow()


    fun getNews(category: String) {
        viewModelScope.launch {
            try {
                val newsResult = newsRepo.getAllNews(category = category)
                _allNewsResponse.value = newsResult.articles
            } catch (e: Exception) {
                Log.d("viewModel", e.message.toString())
            }
        }
    }

    fun getNewsByName(newsName: String) {
        viewModelScope.launch {
            try {
                _allNewsResponse.value = newsRepo.getNewsByName(newsName).articles

            } catch (e: Exception) {
                Log.d("viewModel", e.message.toString())
            }

        }
    }

}








