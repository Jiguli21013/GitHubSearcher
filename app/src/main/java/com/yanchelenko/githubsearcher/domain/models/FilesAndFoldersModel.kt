package com.yanchelenko.githubsearcher.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class FilesAndFoldersModel {

    @Parcelize
    data class FileRepositoryContentItem(
        val htmlUrl: String?,
        val name: String?,
    ) : FilesAndFoldersModel(), Parcelable

    @Parcelize
    data class FolderRepositoryContentItem(
        val name: String?,
        val userName: String?,
        val repoName: String?,
        val path: String?,
    ) : FilesAndFoldersModel(), Parcelable
}
