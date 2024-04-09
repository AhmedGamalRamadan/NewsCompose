package com.game.ag.newscompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.game.ag.newscompose.presentation.screen.home.NewsHomeScreen
import com.game.ag.newscompose.presentation.screen.details.DetailsScreen
import com.game.ag.newscompose.util.Constants

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.HOME
    ) {

        composable(Constants.HOME) {
            NewsHomeScreen(navController)
        }

        composable(
            Constants.DETAILS + "/{newsTitle}/{newsDescription}/{newsSourceName}/{newsDate}",
            arguments = listOf(
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