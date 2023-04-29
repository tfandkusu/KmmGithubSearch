package com.tfandkusu.kgs.di

import com.tfandkusu.kgs.data.remote.GithubRemoteDataSource
import com.tfandkusu.kgs.data.remote.GithubRemoteDataSourceImpl
import com.tfandkusu.kgs.data.remote.getMyHttpClient
import org.koin.dsl.module

val dataRemoteModule = module {
    factory { getMyHttpClient() }
    factory<GithubRemoteDataSource> { GithubRemoteDataSourceImpl(get()) }
}
