package com.game.ag.newscompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.game.ag.newscompose.presentation.screen.home.NewsHomeScreen
import com.game.ag.newscompose.presentation.screen.details.DetailsScreen
import com.game.ag.newscompose.util.Screen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.rout
    ) {

        composable(Screen.HomeScreen.rout) {
            NewsHomeScreen(navController)
        }

        composable(
            Screen.DetailsScreen.rout + "/{newsImage}/{newsTitle}/{newsDescription}/{newsSourceName}/{newsDate}",
            arguments = listOf(
                navArgument("newsImage") { type = NavType.StringType },
                navArgument("newsTitle") { type = NavType.StringType },
                navArgument("newsDescription") { type = NavType.StringType },
                navArgument("newsSourceName") { type = NavType.StringType },
                navArgument("newsDate") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            DetailsScreen(
                backStackEntry,
                navController
            )
        }
    }
}