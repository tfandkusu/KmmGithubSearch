package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.error.ServerException
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

@OptIn(ExperimentalSerializationApi::class)
actual fun getMyHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
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
                    throw ServerException(response.status.value)
                }
            }
        }
    }
}
