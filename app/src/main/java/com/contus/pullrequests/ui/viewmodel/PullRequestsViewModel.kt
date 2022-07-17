package com.contus.pullrequests.ui.viewmodel

import androidx.lifecycle.*
import com.contus.pullrequests.data.model.viewstate.PullRequestResult
import com.contus.pullrequests.data.model.viewstate.PullRequestsViewState
import com.contus.pullrequests.interactor.IPullRequestsInteractor
import com.contus.pullrequests.utils.asyncAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PullRequestsViewModel(private val interactor: IPullRequestsInteractor) : ViewModel() {

    private val viewState: MutableLiveData<PullRequestsViewState> = MutableLiveData(
        PullRequestsViewState()
    )

    fun getViewState() = viewState

    fun getPullRequests() {
        viewState.postValue(
            viewState.value?.copy(
                loading = true
            )
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = asyncAwait { interactor.getClosedPullRequests() }
                viewState.postValue(
                    when (res) {
                        is PullRequestResult.PullRequestSuccess -> {
                            viewState.value?.copy(
                                loading = false,
                                list = res.list,
                                error = null
                            )
                        }

                        is PullRequestResult.PullRequestFailure -> {
                            viewState.value?.copy(
                                loading = false,
                                error = res.message
                            )
                        }
                    }
                )
            }
        }
    }
}