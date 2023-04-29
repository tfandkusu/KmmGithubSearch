package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.home.HomeViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { HomeViewModelImpl(get(), get()) }
}
