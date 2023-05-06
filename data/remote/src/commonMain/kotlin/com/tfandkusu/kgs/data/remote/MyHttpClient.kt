package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.error.MyError
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

expect fun getMyHttpClient(): HttpClient

@OptIn(ExperimentalSerializationApi::class)
fun <T : HttpClientEngineConfig> HttpClientConfig<T>.myConfig() {
    install(HttpTimeout) {
        requestTimeoutMillis = 10000
    }
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                namingStrategy = JsonNamingStrategy.SnakeCase
            },
        )
    }
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
