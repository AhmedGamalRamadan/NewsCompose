package com.game.ag.newscompose.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.game.ag.newscompose.R
import com.game.ag.newscompose.domain.model.Article
import com.game.ag.newscompose.domain.model.Source


@Composable
fun NewsItem(article: Article) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(Color.White),
        shape = RoundedCornerShape(14.dp),
    ) {

        Column(Modifier.padding(8.dp)) {

            val imageState = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .size(Size.ORIGINAL)
                    .build()
            )

            when (imageState.state) {

                is AsyncImagePainter.State.Success -> {

                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        painter = imageState,
                        contentDescription = article.title,
                        contentScale = ContentScale.Crop
                    )
                }

                is AsyncImagePainter.State.Error -> {
                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.ic_error),
                        contentDescription = "Error"
                    )
                }

                is AsyncImagePainter.State.Loading -> {

                    Image(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.ic_loading),
                        contentDescription = "Loading"
                    )
                }

                else -> {}
            }

            Spacer(modifier = Modifier.width(5.dp))



            Text(
                text = article.source.name,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.Start)

            )

            Text(
                text = article.title,
                color = Color.Black,
                fontSize = 13.sp,
            )


            Text(
                text = article.publishedAt,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}


@Preview
@Composable
fun MovieItemPreview() {

    val source = Source("cnn", "CNN")
    val article = Article(
        "BBC",
        "all sources not Newsall sources not Newsall sources not News",
        "The new Mercedes-AMG E53 sedan and wagon share an inline-six engine and electric motor that deliver a combined 603 hp when launch control is activated",
        "2024-3-3", source, "Real Madrid", "", ""
    )
    NewsItem(article)
}








