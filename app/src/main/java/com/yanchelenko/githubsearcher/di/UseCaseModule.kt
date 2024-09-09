package com.yanchelenko.githubsearcher.di

import com.yanchelenko.githubsearcher.domain.usecases.GetListOfDataUseCase
import com.yanchelenko.githubsearcher.domain.usecases.GetRepContentUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetListOfDataUseCase(get(), get()) }
    single { GetRepContentUseCase(get()) }
}
