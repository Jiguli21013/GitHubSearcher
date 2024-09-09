package com.yanchelenko.githubsearcher.data.remote.responses.users

import com.google.gson.annotations.SerializedName
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel.UserInfoModel

data class UserItemResponse(
    @SerializedName("avatar_url") val urlOfAvatar: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("login") val login: String,
    @SerializedName("score") val score: Double,
)

fun UserItemResponse.toDomain() = UserInfoModel (
    avatarUrl = this.urlOfAvatar,
    userLogin = this.login,
    userScore = this.score.toString(),
    htmlUrl = this.htmlUrl
)
