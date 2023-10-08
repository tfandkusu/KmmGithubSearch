package com.tfandkusu.kgs.feature

import com.tfandkusu.kgs.feature.home.SearchGithubUseCase
import com.tfandkusu.kgs.model.GithubRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IosUseCaseHelper : KoinComponent {

    private val searchGithubUseCase: SearchGithubUseCase by inject()

    @Throws(Throwable::class)
    suspend fun searchGithub(keyword: String): List<GithubRepo> {
        return searchGithubUseCase(keyword)
    }
}
