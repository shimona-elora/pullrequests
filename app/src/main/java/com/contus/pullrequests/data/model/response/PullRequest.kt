package com.contus.pullrequests.data.model.response

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("closed_at") val closedAt: String?,
    @SerializedName("user") val user: User?
)

data class User(
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val userImage: String?
)