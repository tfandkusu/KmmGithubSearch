package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.data.remote.schema.GithubSearchResponse
import com.tfandkusu.kgs.model.GithubRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface GithubRemoteDataSource {
    suspend fun search(query: String): List<GithubRepo>
}

/**
 * TODO internalをつける
 */
class GithubRemoteDataSourceImpl(
    private val client: HttpClient
) : GithubRemoteDataSource {
    override suspend fun search(query: String): List<GithubRepo> {
        val httpResponse = client.get(
            "https://api.github.com/search/repositories?q=$query"
        )
        val response: GithubSearchResponse = httpResponse.body()
        return response.items.map {
            GithubRepo(
                it.id,
                it.fullName,
                it.owner.login,
                it.owner.avatarUrl,
                it.language,
                it.stargazersCount,
                it.watchersCount,
                it.forksCount,
                it.openIssuesCount
            )
        }
    }
}
