package com.contus.pullrequests

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ApplicationController: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            module {

            }
        }
    }

}