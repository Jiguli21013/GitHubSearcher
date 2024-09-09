package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.data.remote.api.IRepContentApi
import com.yanchelenko.githubsearcher.data.remote.api.IRepsApi
import com.yanchelenko.githubsearcher.data.remote.api.IUsersApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(IRepsApi::class.java) }
    single { get<Retrofit>().create(IUsersApi::class.java) }
    single { get<Retrofit>().create(IRepContentApi::class.java) }
}