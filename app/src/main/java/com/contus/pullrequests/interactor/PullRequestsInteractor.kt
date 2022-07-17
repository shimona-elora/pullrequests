package com.contus.pullrequests.interactor

import androidx.lifecycle.LiveDataScope
import com.contus.pullrequests.data.IPullRequestsRepository
import com.contus.pullrequests.data.model.PullRequest
import com.contus.pullrequests.data.model.viewstate.PullRequestResult
import com.contus.pullrequests.utils.asyncAwait

class PullRequestsInteractor(private val repository: IPullRequestsRepository) :
    IPullRequestsInteractor {

    override suspend fun getClosedPullRequests(scope: LiveDataScope<PullRequestResult>) {
        val res = asyncAwait {
            repository.getPullRequests()
        }

        scope.emit(res)
    }
}