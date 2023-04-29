package com.tfandkusu.kgs

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.tfandkusu.kgs.di.androidModule
import com.tfandkusu.kgs.di.dataRemoteModuleInFeatureHomeModule
import com.tfandkusu.kgs.di.featureHomeModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        DynamicColors.applyToActivitiesIfAvailable(this)
        startKoin {
            modules(androidModule)
            modules(featureHomeModule)
            modules(dataRemoteModuleInFeatureHomeModule)
        }
    }
}
