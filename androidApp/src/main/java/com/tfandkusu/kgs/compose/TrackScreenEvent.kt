package com.tfandkusu.kgs.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.github.aakira.napier.Napier

/**
 * Firebase Analytics にトラッキングコードを送る Composable 関数。
 * 実装はモック。
 */
@Composable
fun TrackScreenEvent(name: String) {
    LaunchedEffect(Unit) {
        Napier.d("TrackScreen(mock): $name")
    }
}
