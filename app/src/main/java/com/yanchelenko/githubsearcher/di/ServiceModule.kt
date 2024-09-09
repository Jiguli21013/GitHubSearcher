package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.data.remote.services.repContentService.IRepContentService
import com.yanchelenko.githubsearcher.data.remote.services.repContentService.RepContentService
import com.yanchelenko.githubsearcher.data.remote.services.repsService.IRepsService
import com.yanchelenko.githubsearcher.data.remote.services.repsService.RepsService
import com.yanchelenko.githubsearcher.data.remote.services.usersService.IUsersService
import com.yanchelenko.githubsearcher.data.remote.services.usersService.UsersService
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepContentRepository
import org.koin.dsl.module

val serviceModule = module {
    single<IUsersService> { UsersService(get()) }
    single<IRepsService> { RepsService(get()) }
    single<IRepContentService> { RepContentService(get()) }
}
