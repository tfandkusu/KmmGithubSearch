package com.tfandkusu.kgs.data.remote

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * iOSでのHTTPSリクエスト確認用クラス
 */
class TestForIos {
    fun callApi() {
        Napier.base(DebugAntilog())
        Napier.d("callApi")
        GlobalScope.launch {
            Napier.d("GlobalScope.launch")
            val remoteDataSource = GithubRemoteDataSourceImpl(
                getMyHttpClient()
            )
            Napier.d("remoteDataSource")
            val repos = remoteDataSource.search("DroidKaigi")
            Napier.d("remoteDataSource.search")
            val index = repos.indexOfFirst { it.fullName == "DroidKaigi/conference-app-2022" }
            val repo = repos[index]
            Napier.d("fullName = " + repo.fullName)
        }
    }
}
