package com.game.ag.newscompose.presentation.screen.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeScreen(
    navController: NavHostController
) {


    val viewModel = hiltViewModel<NewsViewModel>()
    val generalNewsResponse = viewModel.allNewsResponse.collectAsState().value


    val categoryList =
        listOf("General", "Business", "Technology", "Sports", "Science", "Health", "Entertainment")

    var txtSearchState by remember {
        mutableStateOf("")
    }

//    if (txtSearchState.trim().isNotEmpty()){
//
//    }
    Column {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = txtSearchState,
                onValueChange = { newValue ->
                    txtSearchState = newValue
                    viewModel.getNewsByName(newValue)
                },
                placeholder = { Text(text = "Search ....") },

                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
                },
                shape = RoundedCornerShape(23.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }

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
                        NewsItem(
                            article = article,
                            navHostController = navController
                        )
                    }
                }

            }
        }
    }
