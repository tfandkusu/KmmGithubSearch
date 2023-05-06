package com.tfandkusu.kgs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun getMyHttpClient(): HttpClient {
    return HttpClient(Darwin) {
        myConfig()
    }
}
