package com.tfandkusu.kgs.data.remote.schema

import kotlinx.serialization.Serializable

/**
 * https://api.github.com/search/repositories?q=xxx
 * のレスポンスボディー
 */
@Serializable
data class GithubSearchResponse(val items: List<GithubSearchResponseItem>)

@Serializable
data class GithubSearchResponseItem(
    val id: Long,
    val fullName: String,
    val owner: GithubSearchResponseOwner,
    val language: String?,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int
)

@Serializable
data class GithubSearchResponseOwner(
    val login: String,
    val avatarUrl: String
)
