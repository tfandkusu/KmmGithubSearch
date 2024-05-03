package com.tfandkusu.kgs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.tfandkusu.kgs.compose.AppContent
import com.tfandkusu.kgs.compose.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                AppContent()
            }
        }
    }

    // ERROR. Context is the second parameter
    fun foo(bar: String, context: Context) {
        // 修正した
    }

    // SUCCESS. Context is the first parameter
    fun foo2(context: Context, bar: String) {
    }
}
