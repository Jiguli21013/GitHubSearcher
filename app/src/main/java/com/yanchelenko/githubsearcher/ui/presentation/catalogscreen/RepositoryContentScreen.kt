package com.yanchelenko.githubsearcher.ui.presentation.catalogscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel.FileRepositoryContentItem
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel.FolderRepositoryContentItem
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yanchelenko.githubsearcher.ui.IntentUtils.openUserRepository
import com.yanchelenko.githubsearcher.ui.presentation.ErrorScreen
import com.yanchelenko.githubsearcher.ui.presentation.LoadState
import com.yanchelenko.githubsearcher.ui.presentation.catalogscreen.views.FileRepositoryItem
import com.yanchelenko.githubsearcher.ui.presentation.catalogscreen.views.FolderRepositoryItem

@Composable
fun RepositoryContentScreen(
    repositoryCatalogVM : RepositoryCatalogVM = koinViewModel(),
    onFolderClicked: (FolderRepositoryContentItem) -> Unit
) {
    val listOfItems by repositoryCatalogVM.listOfItems.collectAsState()
    val loadState by repositoryCatalogVM.loadState.collectAsState()

    val context = LocalContext.current

    when (loadState) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Success -> {}
        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = (loadState as LoadState.Error).message.message ?: "Произошла ошибка",
                onRetry = {
                    repositoryCatalogVM.loadContents()
                }
            )
        }
    }

    LazyColumn(

    ) {
        items(
            items = listOfItems,
            key = { it }
        ) { item ->
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray
            )
            when (item) {
                is FolderRepositoryContentItem -> {
                    FolderRepositoryItem(
                        model = item,
                        onFolderClicked = { folder ->
                            onFolderClicked(folder)
                        }
                    )
                }
                is FileRepositoryContentItem -> {
                    FileRepositoryItem(
                        model = item,
                        onFileClicked = { file ->
                            openUserRepository(
                                context = context,
                                htmlUrl = file.htmlUrl ?: ""
                            )
                        }
                    )
                }
            }
        }

    }
    /*
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

     */
}
