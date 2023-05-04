package com.tfandkusu.kgs.di

import org.koin.core.context.startKoin

@Suppress("unused")
fun initKoinForIos() {
    startKoin {
        modules(featureHomeModule)
        modules(dataRemoteModuleInFeatureHomeModule)
    }
}
