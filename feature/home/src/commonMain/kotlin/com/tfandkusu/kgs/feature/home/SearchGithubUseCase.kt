package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.data.remote.GithubRemoteDataSource
import com.tfandkusu.kgs.model.GithubRepo

/**
 * Swift から Kotlin の UseCase クラスの suspend 関数を呼ぶ出す実験のために作ったもの。
 */
interface SearchGithubUseCase {
    suspend operator fun invoke(keyword: String): List<GithubRepo>
}

internal class SearchGithub(
    private val githubRemoteDataSource: GithubRemoteDataSource,
) : SearchGithubUseCase {
    override suspend fun invoke(keyword: String): List<GithubRepo> {
        return githubRemoteDataSource.search(keyword)
    }
}
