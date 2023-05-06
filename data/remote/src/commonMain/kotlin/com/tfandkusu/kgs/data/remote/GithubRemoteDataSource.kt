package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.data.remote.schema.GithubSearchResponse
import com.tfandkusu.kgs.data.remote.schema.HttpBinResponse
import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.model.GithubRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.formUrlEncode
import io.ktor.utils.io.errors.IOException

interface GithubRemoteDataSource {
    /**
     * GitHubリポジトリを検索する
     *
     * @param query 検索キーワード
     * @return リポジトリ一覧(最大30個)
     */
    suspend fun search(query: String): List<GithubRepo>

    /**
     * コルーチンのキャンセルの検証用にレスポンスが遅いHTTPS通信を行う
     */
    suspend fun delay(seconds: Int)

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
    private val client: HttpClient,
) : GithubRemoteDataSource {
    override suspend fun search(query: String): List<GithubRepo> {
        val response = get<GithubSearchResponse>(
            "/search/repositories",
            listOf(
                Pair("q", query),
            ),
        )
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
                it.openIssuesCount,
            )
        }
    }

    override suspend fun delay(seconds: Int) {
        try {
            val httpResponse = client.get(
                "https://httpbin.org/delay/$seconds",
            )
            val response: HttpBinResponse = httpResponse.body()
        } catch (e: IOException) {
            throw MyError.Network
        }
    }

    /**
     * GETメソッドでGitHub APIにリクエストする
     *
     * @param path パスとクエリパラメータ
     */
    private suspend inline fun <reified T> get(
        path: String,
        queries: List<Pair<String, String?>>,
    ): T {
        try {
            val httpResponse = client.get(
                "https://api.github.com$path?" + queries.formUrlEncode(),
            )
            return httpResponse.body()
        } catch (e: IOException) {
            throw MyError.Network
        }
    }

    override suspend fun checkNetworkError() {
        try {
            val httpResponse = client.get(
                "https://hoge.tfandkusu.com/",
            )
            val response: GithubSearchResponse = httpResponse.body()
        } catch (e: IOException) {
            throw MyError.Network
        }
    }

    override suspend fun checkServerError() {
        val httpResponse = client.get(
            "https://mock.codes/404",
        )
        val response: GithubSearchResponse = httpResponse.body()
    }
}
