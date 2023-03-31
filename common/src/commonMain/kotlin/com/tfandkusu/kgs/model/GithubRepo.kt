package com.tfandkusu.kgs.model

/**
 * GitHubレポジトリ
 *
 * @param id ID
 * @param fullName リポジトリ名(例 tfandkusu/android_app_template)
 * @param login 作成者
 * @param avatarUrl 作成者のアバターURL
 * @param language プログラミング言語
 * @param stargazersCount スター数
 * @param watchersCount Watcher数
 * @param forksCount フォーク数
 * @param openIssuesCount OpenなIssue数
 */
data class GithubRepo(
    val id: Long,
    val fullName: String,
    val login: String,
    val avatarUrl: String,
    val language: String?,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int,
)
