package com.yanchelenko.githubsearcher.data.remote.responses.repositories

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("login") val login: String
)