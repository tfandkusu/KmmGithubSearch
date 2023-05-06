package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.model.GithubRepo

sealed class DetailAction {
    data class ShowGitHubRepo(val repo: GithubRepo) : DetailAction()
}
