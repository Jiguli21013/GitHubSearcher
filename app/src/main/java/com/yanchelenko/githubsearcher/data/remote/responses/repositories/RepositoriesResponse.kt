package com.yanchelenko.githubsearcher.data.remote.responses.repositories

import com.google.gson.annotations.SerializedName
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.RepositoryInfoModel

data class RepositoriesResponse(
    @SerializedName("items") val items: List<RepositoryItemResponse>,
)

fun RepositoriesResponse.toDomain(): List<RepositoryInfoModel> =
     this.items.map { response -> response.toDomain() }

