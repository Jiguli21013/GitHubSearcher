package com.yanchelenko.githubsearcher.data.remote.services.repsService

import com.yanchelenko.githubsearcher.data.remote.api.IRepsApi
import com.yanchelenko.githubsearcher.data.remote.responses.repositories.RepositoriesResponse
import com.yanchelenko.githubsearcher.data.remote.services.BaseService

class RepsService(
    private val api: IRepsApi
): BaseService(), IRepsService {
    override suspend fun getReps(searchValue: String, page: Int): Result<RepositoriesResponse> {
        return apiCall { api.getListOfReps(
            searchValue = searchValue,
            page = page
        ) }
    }
}
