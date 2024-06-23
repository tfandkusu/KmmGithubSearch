package com.tfandkusu.kgs.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.tfandkusu.kgs.debug.AnalyticsEventLogHolder
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.get

/**
 * Firebase Analytics にトラッキングコードを送る Composable 関数。
 * 実装はモック。
 */
@Composable
fun TrackScreenEvent(name: String) {
    val logHolder: AnalyticsEventLogHolder = get()
    LaunchedEffect(Unit) {
        logHolder.logEvent(name)
        Napier.d("TrackScreen(mock): $name")
    }
}
