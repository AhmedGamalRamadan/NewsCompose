package com.game.ag.newscompose.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem

@Composable
fun BusinessNewsScreen(viewModel: NewsViewModel) {
    val businessNewsResponse = viewModel.businessNewsList.collectAsState().value

    if (businessNewsResponse.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {

            items(businessNewsResponse) { article ->
                NewsItem(article = article)
            }

        }
    }


}