package com.contus.pullrequests.interactor

import android.util.Log
import com.contus.pullrequests.data.IPullRequestsRepository
import com.contus.pullrequests.data.model.viewstate.PullRequestResult
import com.contus.pullrequests.utils.asyncAwait
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class PullRequestsInteractor(private val repository: IPullRequestsRepository) :
    IPullRequestsInteractor {

    companion object {
        const val TAG = "PullRequestsInteractor"
    }

    private val outputFormatter by lazy {
        DateTimeFormatter.ofPattern("hh:mm a, dd MMM yyyy", Locale.getDefault())
    }

    override suspend fun getClosedPullRequests(): PullRequestResult {
        val res = asyncAwait {
            repository.getPullRequests()
        }

        val finalRes = if (res is PullRequestResult.PullRequestSuccess) {
            val mutableList = res.list?.toMutableList()
            mutableList?.forEachIndexed { index, pullRequest ->
                var createdAt = pullRequest.createdAt
                var closedAt = pullRequest.closedAt
                if (!createdAt.isNullOrEmpty()) {
                    try {
                        createdAt = Instant.parse(createdAt)
                            .atZone(ZoneId.systemDefault())
                            .format(outputFormatter)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message ?: e.stackTraceToString())
                    }
                }
                if (!closedAt.isNullOrEmpty()) {
                    try {
                        closedAt = Instant.parse(closedAt)
                            .atZone(ZoneId.systemDefault())
                            .format(outputFormatter)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message ?: e.stackTraceToString())
                    }
                }
                mutableList[index] = pullRequest.copy(
                    createdAt = createdAt,
                    closedAt = closedAt
                )
            }
            PullRequestResult.PullRequestSuccess(mutableList)
        } else {
            res
        }

        return finalRes
    }
}