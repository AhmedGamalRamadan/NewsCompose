package com.game.ag.newscompose.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem

@Composable
fun NewsHomeScreen(viewModel: NewsViewModel) {

    val generalNewsResponse = viewModel.newsResponse.collectAsState().value

    val categoryList = listOf("General", "Business", "Technology", "Sports", "Science", "Health","Entertainment")


    Column {

        LazyRow {
            items(categoryList) { item ->

                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { viewModel.getNews(item) },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White,
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        pressedElevation = 13.dp,
                        defaultElevation = 2.dp
                    )
                )
                {
                    Text(text = item)
                }
            }
        }

        if (generalNewsResponse.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {

                items(generalNewsResponse) { article ->
                    NewsItem(article = article)
                }
            }

        }
    }


}