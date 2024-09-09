package com.yanchelenko.githubsearcher.ui.presentation.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.githubsearcher.Constants
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel
import com.yanchelenko.githubsearcher.ui.presentation.ErrorScreen
import com.yanchelenko.githubsearcher.ui.presentation.LoadState
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views.RepositoryCard
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views.UserCard
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    searchScreenVM: SearchScreenVM,
    onRepositoryClicked: (CombinedRepsAndUsersModel.RepositoryInfoModel) -> Unit,
    onUserClicked: (CombinedRepsAndUsersModel.UserInfoModel) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val itemsList by searchScreenVM.listOfItems.collectAsState()
    val loadState by searchScreenVM.loadState.collectAsState()
    val searchQuery by searchScreenVM.searchQuery.collectAsState()

    LaunchedEffect(lazyListState) {
        scope.launch {
            snapshotFlow { lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size }
                .collect { visibleItems ->
                    val isLoading = loadState is LoadState.Loading
                    if (visibleItems >= itemsList.size && !isLoading && searchQuery.length >= Constants.MIN_LENGTH_TO_START_SEARCH) {
                        searchScreenVM.getUsersAndRepositories()
                    }
                }
        }
    }

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
                    searchScreenVM.getUsersAndRepositories()
                }
            )
        }
    }

    LazyColumn(
        state = lazyListState,
    ) {
        this.items(items = itemsList) { item ->
            when (item) {
                is CombinedRepsAndUsersModel.UserInfoModel -> {
                    UserCard(
                        userInfoModel = item,
                        onUserClicked = onUserClicked
                    )
                }
                is CombinedRepsAndUsersModel.RepositoryInfoModel -> {
                    RepositoryCard(
                        repositoryModel = item,
                        onRepositoryClicked = onRepositoryClicked
                    )
                }
            }
        }
    }
}
