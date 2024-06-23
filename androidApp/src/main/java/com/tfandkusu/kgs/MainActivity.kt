package com.tfandkusu.kgs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.tfandkusu.kgs.compose.AppContent
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.debug.FAEventOverlayDebugHelper
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val faEventOverlayDebugHelper: FAEventOverlayDebugHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                AppContent()
            }
        }
        faEventOverlayDebugHelper.onCreate(this)
    }

    // ERROR. Context is the second parameter
    fun foo(bar: String, context: Context) {
    }

    // SUCCESS. Context is the first parameter
    fun foo2(context: Context, bar: String) {
    }
}
