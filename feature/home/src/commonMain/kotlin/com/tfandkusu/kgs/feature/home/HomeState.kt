package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.model.GithubRepo

sealed class HomeItem {
    data class Repo(val repo: GithubRepo) : HomeItem()

    object Progress : HomeItem()

    object NetworkError : HomeItem()

    data class ServerError(val statusCode: Int) : HomeItem()
}

data class HomeState(
    val keyword: String,
    val items: List<HomeItem>,
)
