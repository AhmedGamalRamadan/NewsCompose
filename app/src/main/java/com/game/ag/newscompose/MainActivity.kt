package com.game.ag.newscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.model.News
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem

import com.game.ag.newscompose.ui.theme.NewsComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsComposeTheme {

                val viewModel = hiltViewModel<NewsViewModel>()

                viewModel.getTopHeadMovies()

                Text(text = "XXX",
                    Modifier.size(22.dp),
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

//@Composable
//fun NewsList(newsList:List<News>) {
//    LazyColumn{
//        itemsIndexed(items=newsList){index, item ->
//          //  NewsItem(article = item.articles)
//        }
//    }
//}
