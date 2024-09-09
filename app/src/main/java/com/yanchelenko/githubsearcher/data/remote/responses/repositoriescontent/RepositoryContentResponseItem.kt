package com.yanchelenko.githubsearcher.data.remote.responses.repositoriescontent

import com.google.gson.annotations.SerializedName
import com.yanchelenko.githubsearcher.Constants
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel.FileRepositoryContentItem
import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel.FolderRepositoryContentItem

data class RepositoryContentResponseItem(
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("path") val path: String,
    @SerializedName("type") val type: String,
)

fun RepositoryContentResponseItem.toDomain(
    userName: String,
    repoName: String
): FilesAndFoldersModel = if (type == Constants.FILE_TYPE) {
    FileRepositoryContentItem(
        htmlUrl = this.htmlUrl,
        name = this.name
    )
} else {
    FolderRepositoryContentItem(
        name = this.name,
        userName = userName,
        repoName = repoName,
        path = this.path,
    )
}

