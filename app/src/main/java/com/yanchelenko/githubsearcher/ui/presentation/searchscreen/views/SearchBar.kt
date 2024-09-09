package com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanchelenko.githubsearcher.R
import com.yanchelenko.githubsearcher.ui.presentation.LoadState
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.SearchScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchScreenVM: SearchScreenVM) {

    val searchQuery = searchScreenVM.searchQuery.collectAsState().value
    val loadState by searchScreenVM.loadState.collectAsState()
    val isSearching = loadState is LoadState.Loading
    val isError = loadState is LoadState.Error
    val isBtnSearchBlocked by searchScreenVM.isBtnSearchBlocked.collectAsState()

    if (isError) return

    TopAppBar(
        title = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize(),
                singleLine = true,
                maxLines = 1,
                value = searchQuery,
                onValueChange = { newQuery ->
                    searchScreenVM.changeSearchQuery(
                        newSearchQuery = newQuery
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.search_here_title))
                },
                enabled = !isSearching
            )
        },
        actions = {
            IconButton(
                onClick = {
                    searchScreenVM.clearListOfItems()
                    searchScreenVM.getUsersAndRepositories()
                },
                enabled = !isSearching && !isBtnSearchBlocked
            ) {
                Icon(
                    imageVector = if (isSearching || isBtnSearchBlocked) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon_desc)
                )
            }
        }
    )
}
