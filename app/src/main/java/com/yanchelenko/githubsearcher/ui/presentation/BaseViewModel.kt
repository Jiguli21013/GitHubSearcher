package com.yanchelenko.githubsearcher.ui.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel: ViewModel() {
    private val _loadState = MutableStateFlow<LoadState>(LoadState.Success)
    val loadState: StateFlow<LoadState> = _loadState

    fun setLoadState(newLoadState: LoadState) {
        _loadState.value = newLoadState
    }
}
