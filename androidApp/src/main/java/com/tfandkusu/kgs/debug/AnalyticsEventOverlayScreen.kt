package com.tfandkusu.kgs.debug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.tfandkusu.kgs.R
import org.koin.androidx.compose.get

@Composable
fun AnalyticsEventOverlayDebugScreen() {
    val logHolder: AnalyticsEventLogHolder = get()
    val log: List<String> by logHolder.log.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(R.color.fa_event_overlay_debug_background),
                )
                .fillMaxWidth()
                .statusBarsPadding()
                .height(200.dp),
        ) {
            for (eventName in log) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 24.dp)
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    text = eventName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
