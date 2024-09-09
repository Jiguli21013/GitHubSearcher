package com.yanchelenko.githubsearcher.data.remote.responses.users

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("items") val items: List<UserItemResponse>,
)

fun UsersResponse.toDomain() = this.items.map { response -> response.toDomain() }
