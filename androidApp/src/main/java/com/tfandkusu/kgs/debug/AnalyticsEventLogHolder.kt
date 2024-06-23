package com.tfandkusu.kgs.debug

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnalyticsEventLogHolder {

    private val _log = MutableStateFlow<List<AnalyticsEvent>>(listOf())

    val log: StateFlow<List<AnalyticsEvent>> = _log

    fun logEvent(event: AnalyticsEvent) {
        if (_log.value.size >= MAX_LOG_SHOW_COUNT) {
            _log.value = _log.value.drop(1)
        }
        _log.value += event
    }

    companion object {
        private const val MAX_LOG_SHOW_COUNT = 8
    }
}
