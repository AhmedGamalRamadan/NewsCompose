package com.game.ag.newscompose.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem


@Composable
fun HomeScreen(viewModel: NewsViewModel) {

    val response = viewModel.topHeadMovie
    val data = response.collectAsState(initial = emptyList())
    LazyColumn {

        items(data.value) { article ->

            NewsItem(article = article)
        }

    }
}