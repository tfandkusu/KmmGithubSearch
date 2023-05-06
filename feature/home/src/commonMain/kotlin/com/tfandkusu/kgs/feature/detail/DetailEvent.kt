package com.tfandkusu.kgs.feature.detail

import com.tfandkusu.kgs.model.GithubRepo

sealed class DetailEvent {
    data class OnCreate(val repo: GithubRepo) : DetailEvent()
}
