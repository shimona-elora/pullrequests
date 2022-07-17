package com.contus.pullrequests.di

import com.contus.pullrequests.data.IPullRequestsRepository
import com.contus.pullrequests.data.PullRequestsRepository
import com.contus.pullrequests.data.datasource.PullRequestsApiService
import com.contus.pullrequests.interactor.IPullRequestsInteractor
import com.contus.pullrequests.interactor.PullRequestsInteractor
import com.contus.pullrequests.ui.viewmodel.PullRequestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object PullRequestsModule {
    fun getModule() =
        module {
            viewModel {
                PullRequestsViewModel(get())
            }

            single {
                PullRequestsInteractor(get())
            } bind IPullRequestsInteractor::class

            single {
                PullRequestsRepository(get())
            } bind IPullRequestsRepository::class

            single {
                val retrofit: Retrofit by inject()
                retrofit.create(PullRequestsApiService::class.java)
            }
        }
}