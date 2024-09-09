package com.yanchelenko.githubsearcher.data.repositories.interfaces

import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.RepositoryInfoModel

interface IRepSearchRepository {
    suspend fun getRepsList(searchValue: String, page: Int): Result<List<RepositoryInfoModel>>
}
