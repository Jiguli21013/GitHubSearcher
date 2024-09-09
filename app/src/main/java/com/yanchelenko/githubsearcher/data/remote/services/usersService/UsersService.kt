package com.yanchelenko.githubsearcher.data.remote.services.usersService

import com.yanchelenko.githubsearcher.data.remote.api.IUsersApi
import com.yanchelenko.githubsearcher.data.remote.responses.users.UsersResponse
import com.yanchelenko.githubsearcher.data.remote.services.BaseService

class UsersService(
    private val api: IUsersApi
): BaseService(), IUsersService {
    override suspend fun getUsers(searchValue: String, page: Int): Result<UsersResponse> {
        return apiCall { api.getListOfUsers(
            searchValue = searchValue,
            page = page
        ) }
    }
}
