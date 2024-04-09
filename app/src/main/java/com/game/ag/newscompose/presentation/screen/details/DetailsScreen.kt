package com.game.ag.newscompose.presentation.screen.details


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {

    val newsTitle = backStackEntry.arguments?.getString("newsTitle")
    val newsDescription = backStackEntry.arguments?.getString("newsDescription")
    val newsSourceName = backStackEntry.arguments?.getString("newsSourceName")
    val newsDate = backStackEntry.arguments?.getString("newsDate")





    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Article", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {

            Column(Modifier.padding(15.dp)) {

                Text(
                    text = "$newsTitle",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "$newsDescription"
                )

                Spacer(modifier = Modifier.height(32.dp))


                    Text(
                        text = "$newsSourceName",
                        textAlign = TextAlign.Start,
                    )


                    Text(
                        text = "$newsDate",
                        textAlign = TextAlign.End
                    )
            }
        }
    }
}