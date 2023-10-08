package com.tfandkusu.kgs.feature

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.feature.home.SearchGithubUseCase
import com.tfandkusu.kgs.model.GithubRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.cancellation.CancellationException

class IosUseCaseHelper : KoinComponent {

    private val searchGithubUseCase: SearchGithubUseCase by inject()

    @Throws(CancellationException::class, MyError::class)
    suspend fun searchGithub(keyword: String): List<GithubRepo> {
        throw MyError.Network
        return searchGithubUseCase(keyword)
    }
}
