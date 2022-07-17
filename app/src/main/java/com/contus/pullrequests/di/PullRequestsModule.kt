package com.contus.pullrequests.di

import com.contus.pullrequests.ui.viewmodel.PullRequestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PullRequestsModule {
    fun getModule() =
        module {
            viewModel {
                PullRequestsViewModel(get())
            }

            single {
                Pull
            }
        }
}