package com.yanchelenko.githubsearcher.data.remote.services.repsService

import com.yanchelenko.githubsearcher.data.remote.responses.repositories.RepositoriesResponse

interface IRepsService {
    suspend fun getReps(
        searchValue: String,
        page: Int
    ): Result<RepositoriesResponse>
}
