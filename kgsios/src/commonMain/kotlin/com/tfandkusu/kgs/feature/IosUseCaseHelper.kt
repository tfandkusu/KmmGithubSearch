package com.tfandkusu.kgs.feature

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.home.SearchGithubUseCase
import com.tfandkusu.kgs.model.GithubRepoList
import com.tfandkusu.kgs.model.KgsResultSealed
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IosUseCaseHelper : KoinComponent {

    private val searchGithubUseCase: SearchGithubUseCase by inject()

    suspend fun searchGithub(keyword: String): KgsResultSealed<GithubRepoList> {
        return try {
            KgsResultSealed.Success(
                value = GithubRepoList(searchGithubUseCase(keyword)),
            )
        } catch (error: MyError) {
            KgsResultSealed.Failure(
                error = error,
            )
        }
    }
}
