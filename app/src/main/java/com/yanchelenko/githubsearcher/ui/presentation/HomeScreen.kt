package com.yanchelenko.githubsearcher.ui.presentation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yanchelenko.githubsearcher.ui.IntentUtils.openUserRepository
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.SearchScreenVM
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.SearchScreen
import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.views.SearchBar
import com.yanchelenko.githubsearcher.ui.presentation.catalogscreen.RepositoryContentScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    searchScreenViewModel : SearchScreenVM = koinViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val context = LocalContext.current

    Scaffold(
        topBar = {
            if (currentRoute == "SearchScreen") {
                SearchBar(searchScreenVM = searchScreenViewModel)
            }
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "SearchScreen",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
            ) {
                composable(route = "SearchScreen") {
                    SearchScreen(
                        searchScreenVM = searchScreenViewModel,
                        onRepositoryClicked = { repInfoModel ->

                            val encodedUserName = Uri.encode(repInfoModel.ownerName)
                            val encodedRepoName = Uri.encode(repInfoModel.repositoryName)

                            navController.navigate(
                                route = "RepositoryContentScreen/$encodedUserName/$encodedRepoName/${""}"
                            )
                        },
                        onUserClicked = { userInfoModel ->
                            openUserRepository(
                                context = context,
                                htmlUrl = userInfoModel.htmlUrl
                            )
                        }
                    )
                }
                composable(
                    route = "RepositoryContentScreen/{owner}/{repo}/{path}",
                    arguments = listOf(
                        navArgument(name = "owner") { type = NavType.StringType },
                        navArgument(name = "repo") { type = NavType.StringType },
                        navArgument(name = "path") { type = NavType.StringType }
                    )
                ) {
                    RepositoryContentScreen(
                        onFolderClicked = { folder ->
                            val encodedUserName = Uri.encode(folder.userName)
                            val encodedRepoName = Uri.encode(folder.repoName)
                            val encodedPath = Uri.encode(folder.path)
                            navController.navigate(route = "RepositoryContentScreen/$encodedUserName/$encodedRepoName/$encodedPath")
                        }
                    )
                }
            }
        }
    )
}
