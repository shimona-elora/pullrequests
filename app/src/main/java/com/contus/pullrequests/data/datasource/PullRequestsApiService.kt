package com.contus.pullrequests.data.datasource

import com.contus.pullrequests.BuildConfig
import com.contus.pullrequests.data.model.response.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PullRequestsApiService {

    @Headers(
        "Authorization: token ${BuildConfig.TOKEN}",
        "Accept: application/vnd.github+json"
    )
    @GET("repos/{owner}/{repo}/pulls?state=closed")
    fun getClosedPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repoName: String
    ): Call<List<PullRequest>?>

}