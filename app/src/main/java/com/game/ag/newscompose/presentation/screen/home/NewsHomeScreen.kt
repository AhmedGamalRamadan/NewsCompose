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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.presentation.components.NewsItem
import com.game.ag.newscompose.presentation.components.EditTextSearch

@Composable
fun NewsHomeScreen(
    navController: NavHostController
) {


    val viewModel = hiltViewModel<NewsViewModel>()
    val generalNewsResponse = viewModel.allNewsResponse.collectAsState().value


    val categoryList =
        listOf("General", "Business", "Technology", "Sports", "Science", "Health", "Entertainment")

    var categorySelectedIndex by remember { mutableIntStateOf(0) }



    Column {

            EditTextSearch()

        LazyRow {
            itemsIndexed(categoryList) { index, item ->
                val isSelected = index == categorySelectedIndex

                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        categorySelectedIndex = index
                        viewModel.getNews(item)
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
                    NewsItem(
                        article = article,
                        navHostController = navController
                    )
                }
            }
        }
    }
}
