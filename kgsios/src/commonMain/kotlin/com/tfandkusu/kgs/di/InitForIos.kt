package com.tfandkusu.kgs.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

@Suppress("unused")
fun initForIos() {
    Napier.base(DebugAntilog())
    startKoin {
        modules(featureHomeModule)
        modules(dataRemoteModuleInFeatureHomeModule)
    }
}
