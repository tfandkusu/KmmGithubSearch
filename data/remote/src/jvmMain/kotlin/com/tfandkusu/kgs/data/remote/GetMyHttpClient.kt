package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.error.MyError
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

actual fun getMyHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(
            plugin = ContentNegotiation,
            configure = { myConfigure() }
        )
        HttpResponseValidator {
            validateResponse { response ->
                if (response.status.value in 200..299) {
                    // OK
                } else {
                    throw MyError.Server(response.status.value)
                }
            }
        }
    }
}
