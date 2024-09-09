package com.yanchelenko.githubsearcher.data.repositories

import com.yanchelenko.githubsearcher.data.remote.responses.repositories.RepositoriesResponse
import com.yanchelenko.githubsearcher.data.remote.responses.repositories.toDomain
import com.yanchelenko.githubsearcher.data.remote.services.repsService.IRepsService
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepSearchRepository
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.RepositoryInfoModel

class RepSearchRepository(
    private val repService: IRepsService,
): IRepSearchRepository {

    override suspend fun getRepsList(
        searchValue: String,
        page: Int
    ): Result<List<RepositoryInfoModel>> =
        repService.getReps(
            searchValue = searchValue,
            page = page
        ).map(RepositoriesResponse::toDomain)
}
