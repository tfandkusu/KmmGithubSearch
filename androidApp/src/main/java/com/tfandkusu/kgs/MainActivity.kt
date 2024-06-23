package com.tfandkusu.kgs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.tfandkusu.kgs.compose.AppContent
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.debug.AnalyticsEventOverlayHelper
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val analyticsEventOverlayHelper: AnalyticsEventOverlayHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                AppContent()
            }
        }
        analyticsEventOverlayHelper.onCreate(this)
    }
}
