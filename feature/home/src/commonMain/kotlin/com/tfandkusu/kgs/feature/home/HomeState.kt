package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.model.GithubRepo

data class HomeState(
    val keyword: String = "",
    val items: List<Item> = listOf(),
) {
    sealed class Item {
        data class Repo(val repo: GithubRepo) : Item()

        object Progress : Item()

        object NetworkError : Item()

        data class ServerError(val statusCode: Int) : Item()
    }
}
