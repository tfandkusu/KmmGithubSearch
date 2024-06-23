package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.debug.FAEventLogHolder
import com.tfandkusu.kgs.debug.FAEventOverlayHelper
import com.tfandkusu.kgs.home.HomeViewModelImpl
import com.tfandkusu.kgs.util.FA
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { HomeViewModelImpl(get(), get()) }
    factory { FAEventOverlayHelper() }
    single { FAEventLogHolder() }
    single { FA(get()) }
}
