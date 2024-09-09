package com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.yanchelenko.githubsearcher.R

@Composable
fun ImageWithPlaceholder(
    imageUrl: String
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.avatar_icon_desc),
        modifier = Modifier
            .padding(
                start = 12.dp,
                top = 12.dp,
                bottom = 8.dp
            )
            .size(70.dp)
            .padding(
                start = 4.dp,
                top = 4.dp,
                bottom = 4.dp
            )
    )
}
