package com.yanchelenko.githubsearcher.data.remote.responses.repositories

import com.google.gson.annotations.SerializedName
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.RepositoryInfoModel

data class RepositoryItemResponse(
    @SerializedName("description") val descriptionOfRepository: String,
    @SerializedName("forks_count") val countOfForks: Int,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("name") val nameOfRepository: String,
    @SerializedName("owner") val ownerOfRepository: OwnerResponse,
)

fun RepositoryItemResponse.toDomain(): RepositoryInfoModel = RepositoryInfoModel(
    repositoryName = this.nameOfRepository ?: "no name",
    ownerName = this.ownerOfRepository.login,
    forksCount = this.countOfForks ?: 0,
    repositoryDescription = this.descriptionOfRepository ?: "no description",
    htmlUrl = htmlUrl
)
