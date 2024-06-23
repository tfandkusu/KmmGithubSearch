package com.tfandkusu.kgs.util

import com.tfandkusu.kgs.debug.FAEventLogHolder

class FA(
    private val logHolder: FAEventLogHolder,
) {
    fun logEvent(eventName: String) {
        logHolder.logEvent(eventName)
    }
}
