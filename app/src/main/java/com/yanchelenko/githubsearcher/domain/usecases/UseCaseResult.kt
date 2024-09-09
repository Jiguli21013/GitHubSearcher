package com.yanchelenko.githubsearcher.domain.usecases

abstract class UseCaseResult<Input, Output> {
    abstract suspend operator fun invoke(params: Input): Result<Output>
}
