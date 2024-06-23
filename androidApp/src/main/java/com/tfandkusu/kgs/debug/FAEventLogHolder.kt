package com.tfandkusu.kgs.debug

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FAEventLogHolder {

    private val _log = MutableStateFlow<List<String>>(listOf())

    val log: StateFlow<List<String>> = _log

    fun logEvent(event: String) {
        if (_log.value.size >= 8) {
            _log.value = _log.value.drop(1)
        }
        _log.value += event
    }
}
