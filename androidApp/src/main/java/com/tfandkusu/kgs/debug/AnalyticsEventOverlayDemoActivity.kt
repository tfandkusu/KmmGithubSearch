package com.tfandkusu.kgs.debug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.compose.MyTopAppBar
import com.tfandkusu.kgs.compose.TrackScreenEvent
import org.koin.android.ext.android.inject

class AnalyticsEventOverlayDemoActivity : ComponentActivity() {

    private val analyticsEventOverlayHelper: AnalyticsEventOverlayHelper by inject()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                TrackScreenEvent("Demo")
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            title = {
                                Text(stringResource(R.string.fa_event_overlay_demo_title))
                            },
                        )
                    },
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp),
                    ) {
                        Text(stringResource(R.string.fa_event_overlay_demo_body))
                    }
                }
            }
        }
        analyticsEventOverlayHelper.onCreate(this)
    }
}
