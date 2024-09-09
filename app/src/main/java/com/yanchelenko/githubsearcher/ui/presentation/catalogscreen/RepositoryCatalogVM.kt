package com.yanchelenko.githubsearcher.ui.presentation.catalogscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel
import com.yanchelenko.githubsearcher.domain.usecases.GetRepContentUseCase
import com.yanchelenko.githubsearcher.ui.presentation.BaseViewModel
import com.yanchelenko.githubsearcher.ui.presentation.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RepositoryCatalogVM(
    private val getRepContentUseCase: GetRepContentUseCase,
    private val savedState: SavedStateHandle
) : BaseViewModel() {

    val owner = savedState.get<String>("owner")
    val repo = savedState.get<String>("repo")
    val path = savedState.get<String>("path")

    init {
        loadContents()
    }

    private val _listOfItems = MutableStateFlow<List<FilesAndFoldersModel>>(value = listOf())
    val listOfItems: StateFlow<List<FilesAndFoldersModel>> = _listOfItems

    private fun changeListOfItems(newItemsList: List<FilesAndFoldersModel>) {
        _listOfItems.value = newItemsList
    }

    fun loadContents() {
        viewModelScope.launch(Dispatchers.IO) {
            setLoadState(newLoadState = LoadState.Loading)
            delay(2000L)
            getRepContentUseCase.invoke(GetRepContentUseCase.Params(
                owner = owner ?: "",
                repo = repo ?: "",
                path = path ?: ""
            ))
                .onSuccess { newList ->
                    setLoadState(newLoadState = LoadState.Success)
                    changeListOfItems(newItemsList = newList)
                }
                .onFailure { error ->
                    println("---error---${error.localizedMessage}---${error.message}---${error.cause}")
                    setLoadState(newLoadState = LoadState.Error(error))
                }
        }
    }

}
