package com.contus.pullrequests.data.model.viewstate

import com.contus.pullrequests.data.model.PullRequest

data class PullRequestsViewState(
    val loading: Boolean = false,
    val list: List<PullRequest>? = null,
    val error: String? = null
)

sealed class PullRequestResult {
    data class PullRequestSuccess(val list: List<PullRequest>?) : PullRequestResult()
    data class PullRequestFailure(val message: String?) : PullRequestResult()
}