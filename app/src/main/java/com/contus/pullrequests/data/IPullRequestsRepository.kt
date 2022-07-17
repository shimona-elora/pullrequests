package com.contus.pullrequests.data

import com.contus.pullrequests.data.model.PullRequest
import com.contus.pullrequests.data.model.viewstate.PullRequestResult

interface IPullRequestsRepository {

    suspend fun getPullRequests(): PullRequestResult

}