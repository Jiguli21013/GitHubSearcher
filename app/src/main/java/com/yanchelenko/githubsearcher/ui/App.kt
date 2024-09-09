package com.yanchelenko.githubsearcher.ui

import android.app.Application
import com.yanchelenko.githubsearcher.di.apiModule
import com.yanchelenko.githubsearcher.di.networkModule
import com.yanchelenko.githubsearcher.di.repositoryModule
import com.yanchelenko.githubsearcher.di.serviceModule
import com.yanchelenko.githubsearcher.di.useCaseModule
import com.yanchelenko.githubsearcher.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    networkModule,
                    serviceModule,
                    apiModule,
                )
            )
        }
    }
}
