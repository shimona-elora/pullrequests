package com.contus.pullrequests.ui.viewmodel

import androidx.lifecycle.*
import com.contus.pullrequests.data.model.PullRequest
import com.contus.pullrequests.data.model.viewstate.PullRequestResult
import com.contus.pullrequests.data.model.viewstate.PullRequestsViewState
import com.contus.pullrequests.interactor.IPullRequestsInteractor
import kotlinx.coroutines.Dispatchers

class PullRequestsViewModel(private val interactor: IPullRequestsInteractor) : ViewModel() {

    private var viewState: LiveData<PullRequestsViewState> = MutableLiveData()

    fun getViewState() = viewState

    fun getPullRequests() {
        viewState = viewState.switchMap { currentValue ->
            liveData<PullRequestsViewState> {
                emit(currentValue.copy(
                    loading = true
                ))
            }
            liveData<PullRequestResult>(Dispatchers.IO) {
                interactor.getClosedPullRequests(this)
            }.map {
                when (it) {
                    is PullRequestResult.PullRequestSuccess -> {
                        currentValue.copy(
                            loading = false,
                            list = it.list
                        )
                    }

                    is PullRequestResult.PullRequestFailure -> {
                        currentValue.copy(
                            loading = false,
                            error = it.message
                        )
                    }
                }
            }
        }
    }

}