package com.game.ag.newscompose.presentation.screen.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem
import com.game.ag.newscompose.presentation.components.EditTextSearch
import com.game.ag.newscompose.util.checkWifiConnection

@Composable
fun NewsHomeScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val viewModel = hiltViewModel<NewsViewModel>()
    val newsResponse by viewModel.allNewsResponse.collectAsState()

    val categoryList =
        listOf(
            "General",
            "Business",
            "Technology",
            "Sports",
            "Science",
            "Health",
            "Entertainment",
            "Saved"
        )
    val wifiConnected = checkWifiConnection(context)

    var categorySelectedIndex by remember { mutableIntStateOf(0) }

    Column {

        EditTextSearch()

        LazyRow {
            itemsIndexed(categoryList) { index, category ->
                val isSelected = index == categorySelectedIndex

                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        categorySelectedIndex = index
                        if (category == "Saved") {
                            viewModel.getFavoriteNews()
                        } else {
                            viewModel.getNewsByCategory(category)
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = if (isSelected) Color.White else Color.Black,
                        containerColor = if (isSelected) Color.Blue else Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        pressedElevation = 13.dp,
                        defaultElevation = 2.dp
                    )
                ) {
                    Text(text = category)
                }
            }
        }

        if (categorySelectedIndex == categoryList.indexOf("Saved")) {
            if (newsResponse.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "You haven't saved any articles yet"
                    )
                }
            } else {
                LazyColumn {
                    items(newsResponse) { article ->
                        NewsItem(
                            article = article,
                            navHostController = navController
                        ) {
                            viewModel.toggleFavorite(article)
                        }
                    }
                }
            }

        } else {
            if (!wifiConnected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Check Wifi connection")
                }
            } else if (newsResponse.isEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(newsResponse) { article ->
                        NewsItem(
                            article = article,
                            navHostController = navController
                        ) {
                            viewModel.toggleFavorite(article)
                        }
                    }
                }
            }
        }
    }

}


