package com.moviemania.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.moviemania.kmm.MovieInfo

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val state = viewModel.movies.observeAsState(initial = emptyList())
                LazyColumn {
                    items(items = state.value) { item -> RowItem(item) }
                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun RowItem(item: MovieInfo) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp)
        ) {
            val image = rememberImagePainter(imageBaseUrl + item.imageUrl)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = image,
                contentDescription = "Movie poster",
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item.title?.let { Text(text = it, modifier = Modifier.padding(top = 20.dp)) }
                Text(text = item.rating.toString(), modifier = Modifier.padding(top = 20.dp))
            }
        }
    }
}
