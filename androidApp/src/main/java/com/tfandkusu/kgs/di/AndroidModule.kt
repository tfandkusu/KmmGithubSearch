package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.debug.AnalyticsEventLogHolder
import com.tfandkusu.kgs.debug.AnalyticsEventOverlayHelper
import com.tfandkusu.kgs.home.HomeViewModelImpl
import com.tfandkusu.kgs.util.FA
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { HomeViewModelImpl(get(), get()) }
    factory { AnalyticsEventOverlayHelper() }
    single { AnalyticsEventLogHolder() }
    single { FA(get()) }
}
