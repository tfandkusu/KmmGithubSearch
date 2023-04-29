package com.tfandkusu.kgs.feature.home

sealed class HomeEvent {
    data class InputKeyword(val keyword: String) : HomeEvent()

    data class SearchKeyword(val keyword: String) : HomeEvent()
}
