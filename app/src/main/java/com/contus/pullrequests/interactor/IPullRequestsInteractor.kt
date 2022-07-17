package com.contus.pullrequests.interactor

import androidx.lifecycle.LiveDataScope
import com.contus.pullrequests.data.model.viewstate.PullRequestResult

interface IPullRequestsInteractor {

    suspend fun getClosedPullRequests(scope: LiveDataScope<PullRequestResult>)

}