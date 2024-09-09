package com.yanchelenko.githubsearcher.data.remote.services.repContentService

import com.yanchelenko.githubsearcher.data.remote.responses.repositoriescontent.RepositoryContentResponseItem

interface IRepContentService {
    suspend fun getRepContent(
        owner: String,
        repo: String,
        path: String
    ):  Result<List<RepositoryContentResponseItem>>
}
