package com.game.ag.newscompose.presentation.screen.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Downloading
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    val newsImage = backStackEntry.arguments?.getString("newsImage")
    val newsTitle = backStackEntry.arguments?.getString("newsTitle")
    val newsDescription = backStackEntry.arguments?.getString("newsDescription")
    val newsSourceName = backStackEntry.arguments?.getString("newsSourceName")
    val newsDate = backStackEntry.arguments?.getString("newsDate")


    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(newsImage)
            .size(Size.ORIGINAL)
            .build()
    )

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

            Column(Modifier.padding(13.dp)) {

                when (imageState.state) {
                    is AsyncImagePainter.State.Success -> {

                        Image(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(300.dp),
                            painter = imageState,
                            contentDescription = newsTitle,
                            contentScale = ContentScale.Crop
                        )
                    }

                    is AsyncImagePainter.State.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ImageNotSupported,
                                contentDescription = newsTitle,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                    }

                    is AsyncImagePainter.State.Loading -> {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Downloading,
                                contentDescription = newsTitle,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                    }

                    else -> {}
                }

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