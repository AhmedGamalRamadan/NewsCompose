package com.game.ag.newscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.ag.newscompose.presentation.NewsViewModel
import com.game.ag.newscompose.ui.theme.NewsComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.game.ag.newscompose.presentation.screen.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsComposeTheme {

                val viewModel = hiltViewModel<NewsViewModel>()

                HomeScreen(viewModel)


            }
        }
    }
}
