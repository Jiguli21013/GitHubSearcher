package com.yanchelenko.githubsearcher.ui.presentation.searchscreen

import androidx.lifecycle.viewModelScope
import com.yanchelenko.githubsearcher.Constants.MIN_LENGTH_TO_START_SEARCH
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel
import com.yanchelenko.githubsearcher.domain.usecases.GetListOfDataUseCase
import com.yanchelenko.githubsearcher.ui.presentation.BaseViewModel
import com.yanchelenko.githubsearcher.ui.presentation.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchScreenVM(
    private val getListOfDataUseCase: GetListOfDataUseCase,
): BaseViewModel() {

    private val _listOfItems = MutableStateFlow<List<CombinedRepsAndUsersModel>>(value = listOf())
    val listOfItems: StateFlow<List<CombinedRepsAndUsersModel>> = _listOfItems

    private fun changeListOfItems(newItemsList: List<CombinedRepsAndUsersModel>) {
        _listOfItems.value = newItemsList
    }

    fun clearListOfItems() {
        changePage(newPage = 1)
        _listOfItems.value = listOf()
    }

    private val _page = MutableStateFlow<Int>(value = 1)
    val page: StateFlow<Int> = _page

    fun changePage(newPage: Int) {
        _page.value = newPage
    }

    private val _searchQuery = MutableStateFlow<String>(value = "")
    val searchQuery: StateFlow<String> = _searchQuery

    fun changeSearchQuery(newSearchQuery: String) {
        changeIsBtnSearchBlocked(isBtnSearchBlocked = newSearchQuery.length < MIN_LENGTH_TO_START_SEARCH)
        _searchQuery.value = newSearchQuery
    }

    private val _isBtnSearchBlocked = MutableStateFlow<Boolean>(value = true)
    val isBtnSearchBlocked: StateFlow<Boolean> = _isBtnSearchBlocked

    fun changeIsBtnSearchBlocked(isBtnSearchBlocked: Boolean) {
        _isBtnSearchBlocked.value = isBtnSearchBlocked
    }

    fun getUsersAndRepositories() {
        println("---getUsersAndRepositories")
        viewModelScope.launch(Dispatchers.IO) {
            setLoadState(newLoadState = LoadState.Loading)
            delay(6000L)
            getListOfDataUseCase(
                params = GetListOfDataUseCase.Params(
                    searchValue = searchQuery.value,
                    page = page.value
                )
            ).onSuccess { list ->
                setLoadState(newLoadState = LoadState.Success)
                changeListOfItems(newItemsList = listOfItems.value + list)
                changePage(newPage = page.value + 1)
            }.onFailure { error ->
                setLoadState(newLoadState = LoadState.Error(error))
            }
        }
    }
}
