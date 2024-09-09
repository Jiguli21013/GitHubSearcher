package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.data.repositories.RepContentRepository
import com.yanchelenko.githubsearcher.data.repositories.RepSearchRepository
import com.yanchelenko.githubsearcher.data.repositories.UserSearchRepository
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepContentRepository
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepSearchRepository
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IUserSearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IUserSearchRepository> { UserSearchRepository(get()) }
    single<IRepSearchRepository> { RepSearchRepository(get()) }
    single<IRepContentRepository> { RepContentRepository(get()) }
}
