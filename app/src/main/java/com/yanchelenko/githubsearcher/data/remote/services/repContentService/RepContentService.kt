package com.yanchelenko.githubsearcher.data.remote.services.repContentService

import com.yanchelenko.githubsearcher.data.remote.api.IRepContentApi
import com.yanchelenko.githubsearcher.data.remote.responses.repositoriescontent.RepositoryContentResponseItem
import com.yanchelenko.githubsearcher.data.remote.services.BaseService

class RepContentService(
    private val api: IRepContentApi
): BaseService(), IRepContentService {
    override suspend fun getRepContent(
        owner: String,
        repo: String,
        path: String
    ): Result<List<RepositoryContentResponseItem>> {
        return apiCall {
            api.getRepositoryContents(
                owner = owner,
                repo = repo,
                path = path
            )
        }
    }
}
