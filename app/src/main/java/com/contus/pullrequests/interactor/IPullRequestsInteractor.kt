package com.contus.pullrequests.interactor

import com.contus.pullrequests.data.model.viewstate.PullRequestResult

interface IPullRequestsInteractor {

    suspend fun getClosedPullRequests(): PullRequestResult

}