package com.yanchelenko.githubsearcher.domain.usecases

import com.yanchelenko.githubsearcher.data.repositories.interfaces.IRepSearchRepository
import com.yanchelenko.githubsearcher.data.repositories.interfaces.IUserSearchRepository
import com.yanchelenko.githubsearcher.domain.models.CombinedRepsAndUsersModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetListOfDataUseCase(
    private val userSearchRepository: IUserSearchRepository,
    private val repSearchRepository: IRepSearchRepository,
) : UseCaseResult<GetListOfDataUseCase.Params, List<CombinedRepsAndUsersModel>>() {

    override suspend fun invoke(params: Params): Result<List<CombinedRepsAndUsersModel>> = coroutineScope {

        val usersResult = async {
            userSearchRepository.getUsersList(
                searchValue = params.searchValue,
                page = params.page
            )
        }.await()

        val repositoriesResult = async {
            repSearchRepository.getRepsList(
                searchValue = params.searchValue,
                page = params.page
            )
        }.await()

        return@coroutineScope combineAndSortResults(
            repositoriesResult = repositoriesResult,
            usersResult = usersResult
        )
    }

    private fun combineAndSortResults(
        repositoriesResult: Result<List<CombinedRepsAndUsersModel.RepositoryInfoModel>>,
        usersResult: Result<List<CombinedRepsAndUsersModel.UserInfoModel>>
    ): Result<List<CombinedRepsAndUsersModel>> {

        return if (repositoriesResult.isSuccess && usersResult.isSuccess) {
            val repositories = repositoriesResult.getOrThrow()
            val users = usersResult.getOrThrow()

            val combinedList = mutableListOf<CombinedRepsAndUsersModel>()

            repositories.forEach { repo ->
                combinedList.add(repo)
            }

            users.forEach { user ->
                combinedList.add(user)
            }
            val sortedCombinedList = combinedList.sortedWith(compareBy { model ->
                when (model) {
                    is CombinedRepsAndUsersModel.RepositoryInfoModel -> model.repositoryName
                    is CombinedRepsAndUsersModel.UserInfoModel -> model.userLogin
                }
            })

            Result.success(sortedCombinedList)

        } else {
            repositoriesResult.exceptionOrNull()?.let { return Result.failure(it) }
            usersResult.exceptionOrNull()?.let { return Result.failure(it) }

            Result.failure(Throwable("Unknown error"))
        }
    }

    class Params(
        val searchValue: String,
        val page: Int
    )
}


