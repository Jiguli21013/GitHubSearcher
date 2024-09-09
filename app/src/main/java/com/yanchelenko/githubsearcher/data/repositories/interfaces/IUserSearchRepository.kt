package com.yanchelenko.githubsearcher.data.repositories.interfaces

import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.UserInfoModel

interface IUserSearchRepository {
    suspend fun getUsersList(searchValue: String, page: Int): Result<List<UserInfoModel>>
}
