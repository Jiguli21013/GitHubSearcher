package com.yanchelenko.githubsearcher.domain.usecases

import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepContentRepository
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel

class GetRepContentUseCase(
    private val repContentRepository: IRepContentRepository
) : UseCaseResult<GetRepContentUseCase.Params, List<FilesAndFoldersModel>>() {
    override suspend fun invoke(params: Params): Result<List<FilesAndFoldersModel>> {
        return repContentRepository.getRepContent(
            owner = params.owner,
            repo = params.repo,
            path = params.path
        ).map { list ->
            list.sortedWith(compareBy { it is FilesAndFoldersModel.FileRepositoryContentItem })
        }
    }
    class Params(
        val owner: String,
        val repo: String,
        val path: String
    )
}

