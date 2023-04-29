package com.tfandkusu.kgs.feature.home

import com.tfandkusu.kgs.error.MyError
import com.tfandkusu.kgs.model.GithubRepo

sealed class HomeAction {

    data class UpdateList(val repos: List<GithubRepo>) : HomeAction()

    data class UpdateKeyword(val keyword: String) : HomeAction()

    object StartSearch : HomeAction()

    data class Error(val e: MyError) : HomeAction()
}
