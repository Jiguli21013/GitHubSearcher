package com.yanchelenko.githubsearcher.data.remote.api

import com.yanchelenko.githubsearcher.data.remote.responses.users.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IUsersApi {
    @GET("search/users")
    suspend fun getListOfUsers(
        @Query("q") searchValue: String,
        @Query("page") page: Int,
    ): Response<UsersResponse>
}
