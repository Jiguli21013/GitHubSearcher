package com.yanchelenko.githubsearcher.data.repositories

import com.yanchelenko.githubsearcher.data.remote.responses.repositoriescontent.toDomain
import com.yanchelenko.githubsearcher.data.remote.services.repContentService.IRepContentService
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepContentRepository
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel

class RepContentRepository(
    private val repContentService: IRepContentService
): IRepContentRepository {
    override suspend fun getRepContent(
        owner: String,
        repo: String,
        path: String
    ): Result<List<FilesAndFoldersModel>> {
        return repContentService.getRepContent(
            owner = owner,
            repo = repo,
            path = path
        ).map { list ->
            list.map { response ->
                response.toDomain(userName = owner, repoName = repo)
            }
        }
    }
}
