package com.yanchelenko.githubsearcher.ui.presentation

sealed class LoadState {
    object Loading : LoadState()
    data class Error(val message: Throwable) : LoadState()
    object Success : LoadState()
}
