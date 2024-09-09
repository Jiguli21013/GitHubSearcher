package com.yanchelenko.githubsearcher.data.remote.api

import com.yanchelenko.githubsearcher.data.remote.responses.repositories.RepositoryItemResponse
import com.yanchelenko.githubsearcher.data.remote.responses.repositoriescontent.RepositoryContentResponseItem
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface IRepContentApi {
    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getRepositoryContents(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("path") path: String
    ): Response<List<RepositoryContentResponseItem>>
}
