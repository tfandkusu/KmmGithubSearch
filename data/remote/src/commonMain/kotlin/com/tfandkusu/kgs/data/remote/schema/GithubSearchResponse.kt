package com.tfandkusu.kgs.data.remote.schema

data class GithubSearchResponse(val items: List<GithubSearchResponseItem>)

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

data class GithubSearchResponseOwner(
    val login: String,
    val avatarUrl: String
)
