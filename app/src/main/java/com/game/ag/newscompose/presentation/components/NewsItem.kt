package com.game.ag.newscompose.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Downloading
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.util.Screen
import com.game.ag.newscompose.util.encodeUrl


@Composable
fun NewsItem(
    article: Article,
    navHostController: NavHostController,
    onClick: () -> Unit
) {

    var isFavorite by remember { mutableStateOf(article.isFavorite) }

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(article.urlToImage)
            .size(Size.ORIGINAL)
            .build()
    )

    val favoriteIcon: ImageVector = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    val imageUrl = article.urlToImage?.let { imageUrl ->
        encodeUrl(imageUrl)
    } ?: ""

    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(Color.White)
            .clickable {

                navHostController.navigate(Screen.DetailsScreen.rout + "/${imageUrl}/${article.title}/${article.description}/${article.source.name}/${article.publishedAt}")

            }

    ) {

        Column(Modifier.padding(8.dp)) {

            when (imageState.state) {

                is AsyncImagePainter.State.Success -> {

                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(250.dp),
                        painter = imageState,
                        contentDescription = article.title,
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
                            contentDescription = article.title,
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
                            contentDescription = article.title,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                }

                else -> {}
            }


            Text(
                text = article.source.name,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)

            )

            Text(
                text = article.title,
                color = Color.Black,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(5.dp)
            )

            Text(
                text = article.publishedAt,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )

            Box(Modifier.clickable {
                isFavorite = !isFavorite
                onClick()

            }) {
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Favorite",
                )
            }
        }
    }
}






