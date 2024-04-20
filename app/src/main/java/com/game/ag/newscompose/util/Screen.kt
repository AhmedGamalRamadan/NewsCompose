package com.game.ag.newscompose.util

sealed class Screen(val rout: String){
    object HomeScreen : Screen("home")
    object DetailsScreen : Screen("details")
}
