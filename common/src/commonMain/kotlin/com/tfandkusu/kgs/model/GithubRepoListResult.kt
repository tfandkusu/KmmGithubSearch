package com.tfandkusu.kgs.model

import com.tfandkusu.kgs.error.MyError

data class KgsResult<T>(
    val value: T?,
    val error: Throwable?,
)

sealed class KgsResultSealed<T> {
    data class Success<T>(val value: T) : KgsResultSealed<T>()
    data class Failure<T>(val error: MyError) : KgsResultSealed<T>()
}

data class GithubRepoList(
    val repos: List<GithubRepo>,
)
