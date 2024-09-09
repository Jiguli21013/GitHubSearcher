package com.yanchelenko.githubsearcher.data.remote.services.usersService

import com.yanchelenko.githubsearcher.data.remote.responses.users.UsersResponse

interface IUsersService {
    suspend fun getUsers(searchValue: String, page: Int): Result<UsersResponse>
}
