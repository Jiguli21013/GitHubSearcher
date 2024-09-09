package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.data.remote.TransportBuilder
import org.koin.dsl.module

val networkModule = module {
    single { TransportBuilder().buildRetrofit() }
}
