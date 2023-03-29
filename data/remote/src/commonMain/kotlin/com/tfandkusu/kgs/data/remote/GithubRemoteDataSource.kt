package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.model.GithubRepo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

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
        val response = client.get(
            "https://api.github.com/search/repositories?q=droidkaigi"
        )
        response.bodyAsText()
        return listOf()
    }
}
