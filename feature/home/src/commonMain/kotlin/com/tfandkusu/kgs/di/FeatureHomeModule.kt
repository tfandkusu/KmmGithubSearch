package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeReducer
import org.koin.dsl.module

val dataRemoteModuleInFeatureHomeModule = dataRemoteModule

val featureHomeModule = module {
    factory { HomeActionCreator(get()) }
    single { HomeReducer() }
}
