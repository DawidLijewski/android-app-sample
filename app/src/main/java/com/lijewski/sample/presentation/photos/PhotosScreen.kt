package com.lijewski.sample.presentation.photos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.lijewski.sample.data.model.PixabayPhoto
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel = hiltViewModel()
) {
    val photos = viewModel.photosPagingData.collectAsLazyPagingItems()

    LazyColumn {
        items(
            count = photos.itemCount,
            key = photos.itemKey { it.id }
        ) { index ->
            val photo = photos[index]
            if (photo != null) {
                PhotoListItem(photo = photo, modifier = Modifier.fillMaxWidth()) {
                    viewModel.onPhotoClick(photo)
                }
            }
        }
    }
}

@Composable
fun PhotoListItem(photo: PixabayPhoto, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = photo.previewURL,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )
        Column {
            Text(
                text = photo.userName ?: "",
            )
            //TODO: replace with likes icon
            Text(
                text = "Likes: ${photo.likes}",
            )
        }

    }
}

