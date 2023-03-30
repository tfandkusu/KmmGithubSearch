package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.data.remote.schema.GithubSearchResponse
import com.tfandkusu.kgs.model.GithubRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface GithubRemoteDataSource {
    /**
     * GitHubリポジトリを検索する
     *
     * @param query 検索キーワード
     * @return リポジトリ一覧(最大30個)
     */
    suspend fun search(query: String): List<GithubRepo>

    /**
     * ネットワークエラーになるリクエスト
     */
    suspend fun checkNetworkError()

    /**
     * サーバエラーになるリクエスト
     */
    suspend fun checkServerError()
}

internal class GithubRemoteDataSourceImpl(
    private val client: HttpClient
) : GithubRemoteDataSource {
    override suspend fun search(query: String): List<GithubRepo> {
        try {
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
        } catch (e: Throwable) {
            throw mapApiError(e)
        }
    }

    override suspend fun checkNetworkError() {
        try {
            val httpResponse = client.get(
                "https://hoge.tfandkusu.com/"
            )
            val response: GithubSearchResponse = httpResponse.body()
        } catch (e: Throwable) {
            throw mapApiError(e)
        }
    }

    override suspend fun checkServerError() {
        try {
            val httpResponse = client.get(
                "https://mock.codes/404"
            )
            val response: GithubSearchResponse = httpResponse.body()
        } catch (e: Throwable) {
            println(e.toString())
            throw mapApiError(e)
        }
    }
}
