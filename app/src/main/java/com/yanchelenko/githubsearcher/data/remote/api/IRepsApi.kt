package com.yanchelenko.githubsearcher.data.remote.api

import com.yanchelenko.githubsearcher.data.remote.responses.repositories.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRepsApi {
    @GET("search/repositories")
    suspend fun getListOfReps(
        @Query("q") searchValue: String,
        @Query("page") page: Int,
    ): Response<RepositoriesResponse>
}
