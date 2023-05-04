package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.feature.MainScope
import com.tfandkusu.kgs.feature.home.HomeAction
import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeEvent
import com.tfandkusu.kgs.feature.home.HomeReducer
import com.tfandkusu.kgs.feature.viewmodel.Dispatcher
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.mp.KoinPlatform

class HomeKoinComponent : KoinComponent {
    private val homeActionCreator: HomeActionCreator by inject()

    private val homeReducer: HomeReducer by inject()

    private val coroutineScope = MainScope()

    fun createInitialState() {
        Napier.d("KoinPlatform.getKoin() = " + KoinPlatform.getKoin())
        Napier.d("createInitialState = " + homeReducer.createInitialState())

        coroutineScope.launch {
            val dispatcher = object : Dispatcher<HomeAction> {
                override suspend fun dispatch(action: HomeAction) {
                    Napier.d("dispatch = $action")
                }
            }
            homeActionCreator.event(HomeEvent.SearchKeyword("DroidKaigi"), dispatcher)
        }
    }
}
