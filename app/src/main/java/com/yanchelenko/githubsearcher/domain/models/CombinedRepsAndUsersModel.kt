package com.yanchelenko.githubsearcher.domain.models

sealed class CombinedRepsAndUsersModel {
    data class UserInfoModel(
        val avatarUrl: String,
        val userLogin: String,
        val userScore: String,
        val htmlUrl: String
    ) : CombinedRepsAndUsersModel()

    data class RepositoryInfoModel(
        val repositoryName: String,
        val ownerName: String,
        val forksCount: Int,
        val repositoryDescription: String,
        val htmlUrl: String
    ) : CombinedRepsAndUsersModel()
}
