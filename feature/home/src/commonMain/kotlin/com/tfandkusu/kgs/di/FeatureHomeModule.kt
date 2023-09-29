package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.feature.home.HomeActionCreator
import com.tfandkusu.kgs.feature.home.HomeReducer
import com.tfandkusu.kgs.feature.home.SearchGithub
import com.tfandkusu.kgs.feature.home.SearchGithubUseCase
import org.koin.dsl.module

val dataRemoteModuleInFeatureHomeModule = dataRemoteModule

val featureHomeModule = module {
    factory<SearchGithubUseCase> { SearchGithub(get()) }
    factory { HomeActionCreator(get()) }
    single { HomeReducer() }
}
