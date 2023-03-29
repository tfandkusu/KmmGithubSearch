package com.tfandkusu.kgs.model

data class GithubRepo(
    val id: Long,
    val fullName: String,
    val login: String,
    val avatarUrl: String,
    val language: String?,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int
)
