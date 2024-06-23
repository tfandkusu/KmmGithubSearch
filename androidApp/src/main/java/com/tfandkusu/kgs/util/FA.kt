package com.tfandkusu.kgs.util

import com.tfandkusu.kgs.debug.AnalyticsEventLogHolder

class FA(
    private val logHolder: AnalyticsEventLogHolder,
) {
    fun logEvent(eventName: String) {
        logHolder.logEvent(eventName)
    }
}
