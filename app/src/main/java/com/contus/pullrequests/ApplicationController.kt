package com.contus.pullrequests

import android.app.Application
import com.contus.pullrequests.di.PullRequestsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApplicationController : Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)

            modules(
                PullRequestsModule.getModule(),
                getRetrofitClient()
            )
        }
    }

    private fun getRetrofitClient() = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}