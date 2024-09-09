package com.yanchelenko.githubsearcher.data.repositories.interfaces

import com.yanchelenko.githubsearcher.domain.models.FilesAndFoldersModel


interface IRepContentRepository {
    suspend fun getRepContent(
        owner: String,
        repo: String,
        path: String
    ):  Result<List<FilesAndFoldersModel>>
}
