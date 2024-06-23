package com.tfandkusu.kgs.debug

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FAEventLogHolder {

    private val _log = MutableStateFlow<List<String>>(listOf())

    val log: StateFlow<List<String>> = _log

    fun logEvent(event: String) {
        _log.value += event
    }
}
