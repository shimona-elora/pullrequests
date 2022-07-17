package com.contus.pullrequests.data

import com.contus.pullrequests.data.datasource.PullRequestsApiService
import com.contus.pullrequests.data.model.response.PullRequest
import com.contus.pullrequests.data.model.viewstate.PullRequestResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PullRequestsRepository(private val apiService: PullRequestsApiService) :
    IPullRequestsRepository {

    override suspend fun getPullRequests() = suspendCoroutine<PullRequestResult> { cont ->
        val call = apiService.getClosedPullRequests("shimona-elora", "pullrequests")
        call.enqueue(object : Callback<List<PullRequest>?> {
            override fun onResponse(
                call: Call<List<PullRequest>?>,
                response: Response<List<PullRequest>?>
            ) {
                if (response.code() == 200) {
                    cont.resume(PullRequestResult.PullRequestSuccess(response.body()))
                } else {
                    cont.resume(PullRequestResult.PullRequestFailure(response.message()))
                }
            }

            override fun onFailure(call: Call<List<PullRequest>?>, t: Throwable) {
                cont.resume(PullRequestResult.PullRequestFailure(t.message))
            }
        })
    }

}