package com.contus.pullrequests.data

import com.contus.pullrequests.data.model.viewstate.PullRequestResult

class PullRequestsRepository(): IPullRequestsRepository {

    override suspend fun getPullRequests(): PullRequestResult {
        TODO("Not yet implemented")
    }

}