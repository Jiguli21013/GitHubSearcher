package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.ui.presentation.searchscreen.SearchScreenVM
import com.yanchelenko.githubsearcher.ui.presentation.catalogscreen.RepositoryCatalogVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchScreenVM(get()) }
    viewModel { RepositoryCatalogVM(get(), get()) }
}
