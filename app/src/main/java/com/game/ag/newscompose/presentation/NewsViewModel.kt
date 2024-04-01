package com.game.ag.newscompose.presentation

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


    private val _newsResponse = MutableStateFlow<List<Article>>(emptyList())
    val newsResponse = _newsResponse.asStateFlow()


    fun getNews(category: String) {
        viewModelScope.launch {
            val newsResult = newsRepo.getAllNews(category = category)
            _newsResponse.value = newsResult.articles
        }
    }
}








