package com.yanchelenko.githubsearcher.data.repositories

import com.yanchelenko.githubsearcher.data.remote.responses.users.UsersResponse
import com.yanchelenko.githubsearcher.data.remote.responses.users.toDomain
import com.yanchelenko.githubsearcher.data.remote.services.usersService.IUsersService
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IUserSearchRepository
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.UserInfoModel

class UserSearchRepository(
    private val userService: IUsersService,
): IUserSearchRepository {

    override suspend fun getUsersList(
        searchValue: String,
        page: Int
    ): Result<List<UserInfoModel>> =
        userService.getUsers(
            searchValue = searchValue,
            page = page
        ).map(UsersResponse::toDomain)
}
