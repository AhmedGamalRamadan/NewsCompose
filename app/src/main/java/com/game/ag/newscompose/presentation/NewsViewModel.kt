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

    init {
        getSportsNews()
        getBusinessNews()
        getGeneralNews()
    }
    private val _sportNewsList = MutableStateFlow<List<Article>>(emptyList())
    val sportNewsList = _sportNewsList.asStateFlow()


    private val _businessNewsList = MutableStateFlow<List<Article>>(emptyList())
    val businessNewsList = _businessNewsList.asStateFlow()

    private val _generalNewsList =MutableStateFlow<List<Article>>(emptyList())
    val generalNewsList = _generalNewsList.asStateFlow()


    private fun getSportsNews() {
        viewModelScope.launch {
            val sportNewsResult = newsRepo.getAllNews(category = "sports")
            _sportNewsList.value = sportNewsResult.articles

        }
    }

    private fun getBusinessNews() {
        viewModelScope.launch {
            val businessNewsResult = newsRepo.getAllNews(category = "business")
            _businessNewsList.value = businessNewsResult.articles
        }
    }

    private fun getGeneralNews() {
        viewModelScope.launch {
            val generalNewsResult = newsRepo.getAllNews(category = "general")
            _generalNewsList.value = generalNewsResult.articles
        }
    }
}








