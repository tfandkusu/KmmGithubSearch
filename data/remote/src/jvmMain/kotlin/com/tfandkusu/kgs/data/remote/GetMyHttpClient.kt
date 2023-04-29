package com.tfandkusu.kgs.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

actual fun getMyHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(
            plugin = ContentNegotiation,
            configure = { myConfigure() },
        )
        HttpResponseValidator {
            myConfig()
        }
    }
}
