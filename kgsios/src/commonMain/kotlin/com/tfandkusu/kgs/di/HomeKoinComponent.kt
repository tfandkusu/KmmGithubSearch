package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeReducer
import io.github.aakira.napier.Napier
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.mp.KoinPlatform

class HomeKoinComponent : KoinComponent {
    private val homeActionCreator : HomeActionCreator by inject()

    private val homeReducer: HomeReducer by inject()

    fun createInitialState() {
        Napier.d("KoinPlatform.getKoin() = " + KoinPlatform.getKoin())
        Napier.d("createInitialState = " + homeReducer.createInitialState())
    }
}
